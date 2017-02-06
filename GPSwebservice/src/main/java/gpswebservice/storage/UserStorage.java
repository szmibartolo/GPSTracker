package gpswebservice.storage;

import gpswebservice.model.Token;

public interface UserStorage {
    Token loginUser(String username, String password);

    boolean logoutUser(Token token);
}
