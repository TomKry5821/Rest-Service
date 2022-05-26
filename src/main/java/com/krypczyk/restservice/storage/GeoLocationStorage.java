package com.krypczyk.restservice.storage;

import com.krypczyk.restservice.model.GeoLocation;

import java.util.HashMap;
import java.util.Map;

public class GeoLocationStorage {
    private static long id = 1;
    private static GeoLocationStorage instance;
    private Map<Long, GeoLocation> storage;

    private GeoLocationStorage() {
        this.storage = new HashMap<>();
    }

    public static GeoLocationStorage getInstance() {
        if (instance == null) {
            instance = new GeoLocationStorage();
        }
        return instance;
    }

    public Map<Long, GeoLocation> getStorage() {
        return this.storage;
    }

    public void save(GeoLocation geoLocation) {
        this.storage.put(id, geoLocation);
        id += 1;
    }
}
