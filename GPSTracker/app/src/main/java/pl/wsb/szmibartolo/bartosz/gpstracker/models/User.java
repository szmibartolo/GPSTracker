package pl.wsb.szmibartolo.bartosz.gpstracker.models;

import android.databinding.BaseObservable;

/**
 * Created by Maciej on 2016-12-12.
 */

public class User extends BaseObservable {

    private String login;
    private String password;

    public User() {
        this("","");
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
