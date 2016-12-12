package pl.wsb.szmibartolo.bartosz.gpstracker.models;

import android.databinding.BaseObservable;

/**
 * Created by Maciej on 2016-12-12.
 */

public class User {

    private String login;
    private String password;

    public User() {
        this("", "");
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
