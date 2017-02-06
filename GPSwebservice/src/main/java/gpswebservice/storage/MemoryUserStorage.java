package gpswebservice.storage;

import gpswebservice.model.Token;
import gpswebservice.model.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserStorage implements UserStorage {

    private Map<String, User> userMap = new HashMap<>();
    private LoggedUserStorage loggedUserStorage;

    public MemoryUserStorage(LoggedUserStorage loggedUserStorage) {
        this.loggedUserStorage = loggedUserStorage;

        addUser("test1", "test1");
        addUser("bartek", "bpass");
        addUser("zbyszko", "zpass");
    }

    private void addUser(String login, String password) {
        userMap.put(login, new User(login, password));
    }

    @Override
    public Token loginUser(String username, String password) {
        if (userMap.containsKey(username)) {
            User user = userMap.get(username);
            if (user.getPassword().equals(password)) {
                return loggedUserStorage.getTokenForUser(user);
            }
        }

        return null;
    }

    @Override
    public boolean logoutUser(Token token) {
        return loggedUserStorage.logout(token);
    }
}
