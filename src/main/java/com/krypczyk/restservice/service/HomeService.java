package com.krypczyk.restservice.service;

import com.krypczyk.restservice.Exception.InvalidDataException;
import com.krypczyk.restservice.Exception.NotFoundException;
import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.dao.GeoLocationDao;
import com.krypczyk.restservice.model.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    private GeoLocationDao geoLocationDao;

    @Autowired
    private AuthorizationManager authorizationManager;

    public void saveGeolocation(GeoLocation geoLocation, String accessToken) throws InvalidDataException, UnauthorizedException {
        if(geoLocation == null || geoLocation.getDeviceId() == null){
            throw new InvalidDataException("Wrong data format");
        }
        if (!authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }
        this.geoLocationDao.save(geoLocation);
    }

    public List<GeoLocation> getAllGeoLocations(String accessToken) {
        if (!authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }
        return this.geoLocationDao.getAll();
    }

    public GeoLocation getGeoLocationById(String deviceId, String accessToken) {
        if (!authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }

        Optional<GeoLocation> geoLocation = this.geoLocationDao.getByDeviceId(deviceId);

        if (geoLocation.isEmpty()) {
            throw new NotFoundException("Could not find geolocation with provided id");
        }
        return geoLocation.get();
    }
}
