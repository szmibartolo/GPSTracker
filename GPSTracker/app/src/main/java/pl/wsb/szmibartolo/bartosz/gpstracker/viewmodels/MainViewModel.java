package pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.ObservableBoolean;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import pl.wsb.szmibartolo.bartosz.gpstracker.models.ServiceState;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.Stan;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.User;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;
import pl.wsb.szmibartolo.bartosz.gpstracker.utils.GPSController;
import pl.wsb.szmibartolo.bartosz.gpstracker.utils.Http;

public class MainViewModel {

    public ObservableBoolean isVisibleStartWorkButton = new ObservableBoolean();
    public ObservableBoolean isVisibleEndWorkButton = new ObservableBoolean();
    public ObservableBoolean isVisibleStartBreakButton = new ObservableBoolean();
    public ObservableBoolean isVisibleEndBreakButton = new ObservableBoolean();

    private SharedPreferencesStorage sharedPreferencesStorage;

    private Stan stan;
    private User user;
    private GPSController gpsController;

    public MainViewModel(SharedPreferencesStorage sharedPreferencesStorage) {
        this.sharedPreferencesStorage = sharedPreferencesStorage;
        stan = sharedPreferencesStorage.loadStan();
        user = sharedPreferencesStorage.loadUser();
        gpsController = new GPSController(user.getToken());
        updateButtonsVisibility(stan);
    }

    public void onStartWorkButtonClick(View view) {
        updateStan(Stan.ATWORK);

        gpsController.registerLocationUpdate(view.getContext());
        Location location = getLocation(view.getContext());
        Http http = new Http();
        http.sendData(user.getToken(), location.getLatitude(), location.getLongitude(), ServiceState.START_WORK.name().toString(), null);
    }

    public void onEndWorkButtonClick(View view) {
        updateStan(Stan.LOGGEDIN);

        Location location = getLocation(view.getContext());
        Http http = new Http();
        http.sendData(user.getToken(), location.getLatitude(), location.getLongitude(), ServiceState.END_WORK.name().toString(), null);

        gpsController.unregisterLocationUpdate(view.getContext());
    }

    public void onStartBreakButtonClick(View view) {
        updateStan(Stan.ONBREAK);

        Location location = getLocation(view.getContext());
        Http http = new Http();
        http.sendData(user.getToken(), location.getLatitude(), location.getLongitude(), ServiceState.START_BREAK.name().toString(), null);
    }

    public void onEndBreakButtonClick(View view) {
        updateStan(Stan.ATWORK);

        Location location = getLocation(view.getContext());
        Http http = new Http();
        http.sendData(user.getToken(), location.getLatitude(), location.getLongitude(), ServiceState.END_BREAK.name().toString(), null);
    }

    private void updateButtonsVisibility(Stan stan) {
        isVisibleStartWorkButton.set(stan == Stan.LOGGEDIN);
        isVisibleEndWorkButton.set(stan == Stan.ATWORK);
        isVisibleStartBreakButton.set(stan == Stan.ATWORK);
        isVisibleEndBreakButton.set(stan == Stan.ONBREAK);
    }

    private Location getLocation(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location location = gpsController.getLocationManager(context).getLastKnownLocation(LocationManager.GPS_PROVIDER);

        return location;
    }

    private void updateStan(Stan inputStan) {
        stan = inputStan;
        sharedPreferencesStorage.saveStan(stan);
        updateButtonsVisibility(stan);
    }

}
