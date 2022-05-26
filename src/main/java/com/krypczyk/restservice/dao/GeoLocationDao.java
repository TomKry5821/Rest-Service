package com.krypczyk.restservice.dao;

import com.krypczyk.restservice.model.GeoLocation;
import com.krypczyk.restservice.storage.GeoLocationStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeoLocationDao {

    public GeoLocation getByDeviceId(String deviceId) {
        return GeoLocationStorage.getInstance().getStorage().get(deviceId);
    }

    public List<GeoLocation> getAllValues() {
        return new ArrayList<>(GeoLocationStorage.getInstance().getStorage().values());
    }

    public List<String> getAllKeys() {
        return new ArrayList<>(GeoLocationStorage.getInstance().getStorage().keySet());
    }

    public void save(GeoLocation geoLocation, String deviceId) {
        GeoLocationStorage.getInstance().save(geoLocation, deviceId);
    }
}
