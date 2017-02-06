package pl.wsb.szmibartolo.bartosz.gpstracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jakewharton.threetenabp.AndroidThreeTen;

import pl.wsb.szmibartolo.bartosz.gpstracker.databinding.ActivityMainBinding;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;
import pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels.LoginViewModel;
import pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 2312;
    private ActivityMainBinding bindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bindings.setViewModel(new MainViewModel(new SharedPreferencesStorage(this)));

        setSupportActionBar(bindings.toolbar);

        AndroidThreeTen.init(getApplicationContext());
        askForPermission();
    }

    private void askForPermission() {
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if(hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
            }
        }
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_CODE_ASK_PERMISSIONS:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                }
//                break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }

}
