package pl.wsb.szmibartolo.bartosz.gpstracker;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.wsb.szmibartolo.bartosz.gpstracker.databinding.ActivityLoginBinding;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;
import pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding bindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_login);
        bindings.setViewModel(new LoginViewModel(new SharedPreferencesStorage(this)));
    }

}
