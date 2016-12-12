package pl.wsb.szmibartolo.bartosz.gpstracker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.wsb.szmibartolo.bartosz.gpstracker.databinding.ActivityLoginBinding;
import pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private ActivityLoginBinding bindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_login);
        bindings.setViewModel(new LoginViewModel());
    }


}
