package com.krypczyk.restservice.service;

import com.krypczyk.restservice.Exception.InvalidDataException;
import com.krypczyk.restservice.Exception.NotFoundException;
import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.dao.GeoLocationDao;
import com.krypczyk.restservice.dto.GeoLocationDto;
import com.krypczyk.restservice.model.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HomeService {

    @Autowired
    private GeoLocationDao geoLocationDao;

    @Autowired
    private AuthorizationManager authorizationManager;

    public void saveGeolocation(GeoLocationDto geoLocationDto, String accessToken) throws InvalidDataException, UnauthorizedException {
        if (!authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }
        if (Objects.isNull(geoLocationDto) || Objects.isNull(geoLocationDto.getDeviceId())) {
            throw new InvalidDataException("Wrong data format");
        }

        GeoLocation geoLocation = new GeoLocation(geoLocationDto.getLatitude(), geoLocationDto.getLongitude());
        String deviceId = geoLocationDto.getDeviceId();

        this.geoLocationDao.save(geoLocation, deviceId);
    }

    public List<GeoLocationDto> getAllGeoLocations(String accessToken) {
        if (!authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }
        var values = this.geoLocationDao.getAllValues();
        var keys = this.geoLocationDao.getAllKeys();

        List<GeoLocationDto> locations = new ArrayList<>();

        for (int i = 0; i < keys.size(); i++) {
            GeoLocationDto geoLocationDto = new GeoLocationDto(keys.get(i), values.get(i).getLatitude(), values.get(i).getLongitude());
            locations.add(geoLocationDto);
        }

        return locations;
    }

    public GeoLocationDto getGeoLocationById(String deviceId, String accessToken) {
        if (!authorizationManager.isAuthorized(accessToken)) {
            throw new UnauthorizedException("Unauthorized user");
        }

        GeoLocation geoLocation = this.geoLocationDao.getByDeviceId(deviceId);
        if (Objects.isNull(geoLocation)) {
            throw new NotFoundException("Could not find geolocation with provided id");
        }

        return new GeoLocationDto(deviceId, geoLocation.getLatitude(), geoLocation.getLongitude());
    }
}
