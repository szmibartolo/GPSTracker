package pl.wsb.szmibartolo.bartosz.gpstracker.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import pl.wsb.szmibartolo.bartosz.gpstracker.R;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Grzegorz on 15-01-2017.
 */

public class Utils {

    public static boolean isNetworkConnected(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status == true) {
            Toast.makeText(context, context.getResources().getText(R.string.network_is_on), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getResources().getText(R.string.network_is_off), Toast.LENGTH_SHORT).show();
        }

        return status;
    }

    public static boolean isGPSConnected(Context context){
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(context, context.getResources().getText(R.string.gps_is_on), Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(context, context.getResources().getText(R.string.gps_is_off), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
