package pl.wsb.szmibartolo.bartosz.gpstracker.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import pl.wsb.szmibartolo.bartosz.gpstracker.models.User;

/**
 * Created by Maciej on 2016-12-12.
 */

public class SharedPreferencesStorage {

    private static final String USER_KEY_LOGIN = "USER_KEY_LOGIN";
    private static final String USER_KEY_PASS = "USER_KEY_PASS";
    private Activity activity;

    public SharedPreferencesStorage(Activity activity) {
        this.activity = activity;
    }

    public void saveUser(User user) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_KEY_LOGIN, user.getLogin());
        editor.putString(USER_KEY_PASS, user.getPassword());
        editor.commit();
    }


    public User loadUser(){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);

        User user = new User();
        user.setLogin(sharedPref.getString(USER_KEY_LOGIN, ""));
        user.setPassword(sharedPref.getString(USER_KEY_PASS, ""));

        return user;
    }

}
