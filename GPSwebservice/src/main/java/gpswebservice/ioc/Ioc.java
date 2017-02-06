package gpswebservice.ioc;

import gpswebservice.storage.*;

public class Ioc {

    private static Ioc me = new Ioc();
    private Holder holder;

    private Ioc() {
        // private constructor
    }

    public static Ioc getInstance() {
        return me;
    }

    public synchronized void init() {
        if (holder == null) {
            this.holder = new Holder();
        }
    }

    public UserStorage getUserStorage() {
        return holder.userStorage;
    }

    public StatusStorage getStatusStorage() {
        return holder.statusStorage;
    }

    private class Holder {
        private StatusStorage statusStorage;
        private UserStorage userStorage;
        private LoggedUserStorage loggedUserStorage;

        public Holder() {
            statusStorage = new MemoryStatusStorage();
            loggedUserStorage = new MemoryLoggedUserStorage();
            userStorage = new MemoryUserStorage(loggedUserStorage);
        }
    }
}
