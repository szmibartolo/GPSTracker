package gpswebservice.storage;

import gpswebservice.model.Location;
import gpswebservice.model.User;

import java.util.List;

public interface LocationStorage {
    void add(User user, Location location);

    List<Location> getLocations(String userlogin);
}
