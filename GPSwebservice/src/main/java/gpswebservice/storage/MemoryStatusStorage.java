package gpswebservice.storage;

import gpswebservice.model.Status;

import java.time.LocalDateTime;

public class MemoryStatusStorage implements StatusStorage {

    private final LocalDateTime startServerDateTime;

    public MemoryStatusStorage() {
        startServerDateTime = LocalDateTime.now();
    }

    @Override
    public Status getStatus() {
        return new Status(startServerDateTime);
    }
}
