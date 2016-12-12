package pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import pl.wsb.szmibartolo.bartosz.gpstracker.MainActivity;

/**
 * Created by Maciej on 2016-12-12.
 */

public class LoginViewModel {

    private final android.databinding.Observable.OnPropertyChangedCallback editTextPropertyListener;

    public ObservableField<String> login = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableBoolean enableLoginButton = new ObservableBoolean(false);

    public LoginViewModel() {
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
        Context context = view.getContext();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void updateLoginButton() {
        boolean isSomeTextInLogin = login.get() != null && login.get().length() > 0;
        boolean isSomeTextInPassword = password.get() != null && password.get().length() > 0;
        enableLoginButton.set(isSomeTextInLogin && isSomeTextInPassword);
    }
}
