package gpswebservice.model;

import gpswebservice.utils.LocalDateTimeFormatter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Status {
    private final String serverStartTime;
    private final long serverIdleTimeInSeconds;

    public Status(LocalDateTime serverStartTime) {
        this.serverStartTime = LocalDateTimeFormatter.parse(serverStartTime);
        this.serverIdleTimeInSeconds = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - serverStartTime.toEpochSecond(ZoneOffset.UTC);
    }

    public String getServerStartTime() {
        return serverStartTime;
    }

    public long getServerIdleTimeInSeconds() {
        return serverIdleTimeInSeconds;
    }
}
