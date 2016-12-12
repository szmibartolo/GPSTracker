package pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import pl.wsb.szmibartolo.bartosz.gpstracker.MainActivity;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.Stan;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.User;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;

/**
 * Created by Maciej on 2016-12-12.
 */

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

        if (stan == Stan.LOGGEDIN) {
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

    public void onClickLoginButton(View view) {
        user = new User(login.get(), password.get());
        sharedPreferencesStorage.saveUser(user);
sharedPreferencesStorage.saveStan(Stan.LOGGEDIN);
        Context context = view.getContext();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void updateLoginButton() {
        boolean isSomeTextInLogin = login.get() != null && login.get().length() > 0;
        boolean isSomeTextInPassword = password.get() != null && password.get().length() > 0;
        enableLoginButton.set(isSomeTextInLogin && isSomeTextInPassword);
    }

}
