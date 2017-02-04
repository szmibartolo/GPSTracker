package gpswebservice.ioc;

import gpswebservice.storage.MemoryStatusStorage;
import gpswebservice.storage.StatusStorage;

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

    public StatusStorage getStatusStorage() {
        return holder.statusStorage;
    }

    private class Holder {
        private StatusStorage statusStorage;

        public Holder() {
            statusStorage = new MemoryStatusStorage();
        }
    }
}
