package com.krypczyk.restservice.service;

import com.krypczyk.restservice.Exception.InvalidDataException;
import com.krypczyk.restservice.Exception.NotFoundException;
import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.model.GeoLocation;
import com.krypczyk.restservice.storage.GeoLocationStorage;
import com.krypczyk.restservice.storage.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeServiceTest {

    @Autowired
    private HomeService homeService;

    @BeforeEach
    void setUp() {
        UserStorage.getInstance().getStorage().get(1L).setAccessToken("token");
    }

    @Test
    void testSaveGeolocationWithValidData() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");
        UserStorage.getInstance().getStorage().get(1L).setAccessToken("token");

        this.homeService.saveGeolocation(geoLocation, "token");

        assertDoesNotThrow(() -> InvalidDataException.class);

    }

    @Test
    void testSaveGeolocationWithInvalidData() {
        GeoLocation geoLocation = null;
        assertThrows(InvalidDataException.class, () -> this.homeService.saveGeolocation(geoLocation, "token"));
    }

    @Test
    void testSaveGeolocationWithValidToken() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");

        this.homeService.saveGeolocation(geoLocation, "token");

        assertDoesNotThrow(() -> UnauthorizedException.class);

    }

    @Test
    void testSaveGeolocationWithInvalidToken() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");

        assertThrows(UnauthorizedException.class, () -> this.homeService.saveGeolocation(geoLocation, null));
    }

    @Test
    void testGetAllGeoLocationsWithValidToken() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");
        GeoLocationStorage.getInstance().save(geoLocation);
        var expectedResult = GeoLocationStorage.getInstance().getStorage().values().stream().toList();

        var result = this.homeService.getAllGeoLocations("token");

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllGeoLocationsWithInvalidToken() {
        assertThrows(UnauthorizedException.class, () -> this.homeService.getAllGeoLocations(null));
    }

    @Test
    void testGetGeoLocationByIdWithValidId() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");
        var expectedResult = geoLocation;
        GeoLocationStorage.getInstance().save(geoLocation);

        var result = this.homeService.getGeoLocationById("id", "token");

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetGeoLocationByIdWithInvalidId() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");
        GeoLocationStorage.getInstance().save(geoLocation);

        assertThrows(NotFoundException.class, () -> this.homeService.getGeoLocationById("wrongId", "token"));
    }

    @Test
    void testGetGeoLocationByIdWithValidToken() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");
        var expectedResult = geoLocation;
        GeoLocationStorage.getInstance().save(geoLocation);

        var result = this.homeService.getGeoLocationById("id", "token");

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetGeoLocationByIdWithInvalidToken() {
        GeoLocation geoLocation = new GeoLocation("id", "232323", "445454");
        GeoLocationStorage.getInstance().save(geoLocation);

        assertThrows(UnauthorizedException.class, () -> this.homeService.getGeoLocationById("id", "wrongToken"));
    }
}