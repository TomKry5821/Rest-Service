package com.krypczyk.restservice.dao;

import com.krypczyk.restservice.model.GeoLocation;
import com.krypczyk.restservice.storage.GeoLocationStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GeoLocationDao implements Dao<GeoLocation> {

    public Optional<GeoLocation> getByDeviceId(String deviceId) {
        return Optional.of(GeoLocationStorage.getInstance().getStorage().values().stream().filter(g -> g.getDeviceId().equals(deviceId)).findFirst()).get();
    }

    @Override
    public List<GeoLocation> getAll() {
        return new ArrayList<>(GeoLocationStorage.getInstance().getStorage().values());
    }

    @Override
    public void save(GeoLocation geoLocation) {
        GeoLocationStorage.getInstance().save(geoLocation);
    }
}
