package gpswebservice.model;

import gpswebservice.utils.LocalDateTimeFormatter;

import java.time.LocalDateTime;

public class Location {
    private final String userLogin;
    private final String localDateTimeString;
    private final LocalDateTime localDateTime;
    private final LatLong latLong;
    private final Type typ;

    public Location(String userLogin, LocalDateTime localDateTime, LatLong latLong, Type typ) {
        this.userLogin = userLogin;
        this.localDateTime = localDateTime;
        this.latLong = latLong;
        this.typ = typ;
        this.localDateTimeString = LocalDateTimeFormatter.parse(localDateTime);
    }

    public String getUserLogin() {
        return userLogin;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public LatLong getLatLong() {
        return latLong;
    }

    public Type getTyp() {
        return typ;
    }

    public String getLocalDateTimeString() {
        return localDateTimeString;
    }
}
