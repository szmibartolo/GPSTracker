package pl.wsb.szmibartolo.bartosz.gpstracker.storage;

import android.content.Context;
import android.content.SharedPreferences;

import pl.wsb.szmibartolo.bartosz.gpstracker.models.Stan;
import pl.wsb.szmibartolo.bartosz.gpstracker.models.User;

/**
 * Created by Maciej on 2016-12-12.
 */

public class SharedPreferencesStorage {

    private static final String SHERED_PREF = "SharedPreferencesStorage";

    private static final String USER_KEY_LOGIN = "USER_KEY_LOGIN";
    private static final String USER_KEY_PASS = "USER_KEY_PASS";
    private static final String STAN_KEY = "STAN_KEY";

    private Context context;

    public SharedPreferencesStorage(Context context) {
        this.context = context;
    }

    public void saveUser(User user) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHERED_PREF, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_KEY_LOGIN, user.getLogin());
        editor.putString(USER_KEY_PASS, user.getPassword());
        editor.commit();
    }

    public User loadUser() {
        SharedPreferences sharedPref = context.getSharedPreferences(SHERED_PREF, 0);

        User user = new User();
        user.setLogin(sharedPref.getString(USER_KEY_LOGIN, ""));
        user.setPassword(sharedPref.getString(USER_KEY_PASS, ""));

        return user;
    }

    public void saveStan(Stan stan) {
        SharedPreferences sharedPref = context.getSharedPreferences(SHERED_PREF, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(STAN_KEY, stan.ordinal());
        editor.commit();

    }

    public Stan loadStan() {
        SharedPreferences sharedPref = context.getSharedPreferences(SHERED_PREF, 0);
        return Stan.values()[sharedPref.getInt(STAN_KEY, 0)];
    }
}
