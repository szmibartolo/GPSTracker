package gpswebservice.storage;

import gpswebservice.model.Token;
import gpswebservice.model.User;
import gpswebservice.utils.TokenGenerator;

import java.util.HashMap;
import java.util.Map;

public class MemoryLoggedUserStorage implements LoggedUserStorage {
    private Map<Token, User> userMap = new HashMap<>();

    @Override
    public Token getTokenForUser(User user) {
        Token token = user.getToken();
        if (token == null) {
            token = TokenGenerator.generateNewToken(user.getLogin());
            user.setToken(token);
            userMap.put(token, user);
        }
        return token;
    }

    @Override
    public boolean logout(Token token) {
        if (userMap.containsKey(token)) {
            User user = userMap.get(token);
            user.setToken(null);
            userMap.remove(token);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(Token token) {
        if (userMap.containsKey(token))
            return userMap.get(token);

        return null;
    }
}
