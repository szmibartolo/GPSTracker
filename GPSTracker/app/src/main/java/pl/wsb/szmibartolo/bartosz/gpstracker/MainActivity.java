package pl.wsb.szmibartolo.bartosz.gpstracker;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import pl.wsb.szmibartolo.bartosz.gpstracker.databinding.ActivityMainBinding;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;
import pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels.LoginViewModel;
import pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bindings.setViewModel(new MainViewModel(new SharedPreferencesStorage(this)));

        setSupportActionBar(bindings.toolbar);
    }

}
