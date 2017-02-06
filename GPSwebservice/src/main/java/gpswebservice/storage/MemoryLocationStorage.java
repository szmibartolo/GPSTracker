package gpswebservice.storage;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import gpswebservice.model.Location;
import gpswebservice.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemoryLocationStorage implements LocationStorage {

    private Multimap<String, Location> myMultimap = ArrayListMultimap.create();

    @Override
    public void add(User user, Location location) {
        myMultimap.put(user.getLogin(), location);
    }

    @Override
    public List<Location> getLocations(String userlogin) {
        Collection<Location> locationCollection = myMultimap.get(userlogin);

        if (locationCollection instanceof List)
            return  (List<Location>)locationCollection;
        else
            return new ArrayList<Location>(locationCollection);
    }
}
