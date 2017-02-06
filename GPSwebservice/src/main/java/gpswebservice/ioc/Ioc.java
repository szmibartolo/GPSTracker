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

    public LoggedUserStorage getLoggedUserStorage() {
        return holder.loggedUserStorage;
    }

    public LocationStorage getLocationStorage() {
        return holder.locationStorage;
    }

    private class Holder {
        private StatusStorage statusStorage;
        private UserStorage userStorage;
        private LoggedUserStorage loggedUserStorage;
        private LocationStorage locationStorage;

        public Holder() {
            statusStorage = new MemoryStatusStorage();
            loggedUserStorage = new MemoryLoggedUserStorage();
            userStorage = new MemoryUserStorage(loggedUserStorage);
            locationStorage = new MemoryLocationStorage();
        }
    }
}
