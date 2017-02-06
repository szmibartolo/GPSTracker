package gpswebservice.controlers;

import gpswebservice.controlers.errors.UnauthorazedException;
import gpswebservice.ioc.Ioc;
import gpswebservice.model.*;
import gpswebservice.storage.LocationStorage;
import gpswebservice.storage.LoggedUserStorage;
import gpswebservice.utils.LocalDateTimeFormatter;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GpsController {

    private LoggedUserStorage loggedUserStorage;
    private LocationStorage locationStorage;

    public GpsController() {
        loggedUserStorage = Ioc.getInstance().getLoggedUserStorage();
        locationStorage = Ioc.getInstance().getLocationStorage();
    }

    @RequestMapping("/loggedwork")
    public List<Location> getLocationsForUser(@RequestParam("user") String userlogin) {
        return locationStorage.getLocations(userlogin);
    }

    @RequestMapping("/work")
    public Token logWork(@RequestHeader("X-Auth-Token") String xtoken,
                         @RequestParam("date") String date,
                         @RequestParam("lat") double lattitude,
                         @RequestParam("long") double longinitude,
                         @RequestParam(value = "type", defaultValue = "SEND_GPS") Type typ) {

        User user = loggedUserStorage.getUser(new Token(xtoken));

        if (user != null) {
            Location location = new Location(user.getLogin(),
                    LocalDateTimeFormatter.parse(date),
                    new LatLong(lattitude, longinitude),
                    typ);
            locationStorage.add(user, location);
        } else {
            throw new UnauthorazedException();
        }

        return user.getToken();
    }
}
