package gpswebservice.storage;

import gpswebservice.model.Token;
import gpswebservice.model.User;

public interface LoggedUserStorage {
    Token getTokenForUser(User user);

    boolean logout(Token token);

    User getUser(Token token);
}
