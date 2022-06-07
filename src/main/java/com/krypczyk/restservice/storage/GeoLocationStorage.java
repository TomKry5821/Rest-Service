package com.krypczyk.restservice.storage;

import com.krypczyk.restservice.model.GeoLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GeoLocationStorage {
    private static GeoLocationStorage instance;
    private Map<String, GeoLocation> storage;

    private GeoLocationStorage() {
        this.storage = new HashMap<>();
    }

    public static GeoLocationStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new GeoLocationStorage();
        }
        return instance;
    }

    public Map<String, GeoLocation> getStorage() {
        return this.storage;
    }

    public void save(GeoLocation geoLocation, String deviceId) {
        this.storage.put(deviceId, geoLocation);
    }
}
