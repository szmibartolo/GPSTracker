package pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import pl.wsb.szmibartolo.bartosz.gpstracker.MainActivity;
import pl.wsb.szmibartolo.bartosz.gpstracker.R;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.Stan;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.User;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;
import pl.wsb.szmibartolo.bartosz.gpstracker.utils.Http;
import pl.wsb.szmibartolo.bartosz.gpstracker.utils.HttpResultInterface;
import pl.wsb.szmibartolo.bartosz.gpstracker.utils.Utils;

public class LoginViewModel {

    private final android.databinding.Observable.OnPropertyChangedCallback editTextPropertyListener;
    private Stan stan;

    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableBoolean enableLoginButton = new ObservableBoolean(false);

    private User user;
    private SharedPreferencesStorage sharedPreferencesStorage;

    public LoginViewModel(SharedPreferencesStorage sharedPreferencesStorage) {
        this.sharedPreferencesStorage = sharedPreferencesStorage;
        user = sharedPreferencesStorage.loadUser();
        stan = sharedPreferencesStorage.loadStan();

        if (stan == Stan.NOTLOGGEDIN) {
            // to do load next activity
        }

        login.set(user.getLogin());
        password.set(user.getPassword());

        editTextPropertyListener = new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable observable, int i) {
                updateLoginButton();
            }
        };

        login.addOnPropertyChangedCallback(editTextPropertyListener);
        password.addOnPropertyChangedCallback(editTextPropertyListener);
    }

    public void onClickLoginButton(final View view) {
        if(Utils.isNetworkConnected(view.getContext()) && Utils.isGPSConnected(view.getContext())) {
            Http http = new Http();
            http.login(login.get(), password.get(), new HttpResultInterface() {
                @Override
                public void onResult(int httpCode, String token) {
                    if (httpCode == 200 || httpCode == 201) {
                        user = new User(login.get(), password.get(), token);
                        sharedPreferencesStorage.saveUser(user);
                        if (stan == Stan.NOTLOGGEDIN)
                            sharedPreferencesStorage.saveStan(Stan.LOGGEDIN);
                        Context context = view.getContext();
                        context.startActivity(new Intent(context, MainActivity.class));
                    } else {
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(view.getContext(), view.getResources().getText(R.string.cant_log_in), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
    }

    private void updateLoginButton() {
        boolean isSomeTextInLogin = login.get() != null && login.get().length() > 0;
        boolean isSomeTextInPassword = password.get() != null && password.get().length() > 0;
        enableLoginButton.set(isSomeTextInLogin && isSomeTextInPassword);
    }

}
