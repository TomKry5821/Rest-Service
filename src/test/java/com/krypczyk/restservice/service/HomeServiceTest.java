package com.krypczyk.restservice.service;

import com.krypczyk.restservice.Exception.InvalidDataException;
import com.krypczyk.restservice.Exception.NotFoundException;
import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.dto.GeoLocationDto;
import com.krypczyk.restservice.model.GeoLocation;
import com.krypczyk.restservice.storage.GeoLocationStorage;
import com.krypczyk.restservice.storage.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeServiceTest {

    @Autowired
    private HomeService homeService;

    private GeoLocationDto geoLocationDto;

    @BeforeEach
    void setUp() {
        UserStorage.getInstance().getStorage().get(1L).setAccessToken("token");
        geoLocationDto = new GeoLocationDto("id", "232323", "445454");
    }

    @Test
    void testSaveGeolocationWithValidData() {
        UserStorage.getInstance().getStorage().get(1L).setAccessToken("token");

        this.homeService.saveGeolocation(geoLocationDto, "token");

        assertDoesNotThrow(() -> InvalidDataException.class);

    }

    @Test
    void testSaveGeolocationWithInvalidData() {
        GeoLocationDto geoLocationDto = null;
        assertThrows(InvalidDataException.class, () -> this.homeService.saveGeolocation(geoLocationDto, "token"));
    }

    @Test
    void testSaveGeolocationWithValidToken() {

        this.homeService.saveGeolocation(geoLocationDto, "token");

        assertDoesNotThrow(() -> UnauthorizedException.class);

    }

    @Test
    void testSaveGeolocationWithInvalidToken() {

        assertThrows(UnauthorizedException.class, () -> this.homeService.saveGeolocation(geoLocationDto, null));
    }

    @Test
    void testGetAllGeoLocationsWithValidToken() {
        GeoLocation geoLocation = new GeoLocation("232323", "445454");
        GeoLocationStorage.getInstance().save(geoLocation, "id");
        var expectedResult = Arrays.asList(geoLocationDto);

        var result = this.homeService.getAllGeoLocations("token");

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllGeoLocationsWithInvalidToken() {
        assertThrows(UnauthorizedException.class, () -> this.homeService.getAllGeoLocations(null));
    }

    @Test
    void testGetGeoLocationByIdWithValidId() {
        GeoLocation geoLocation = new GeoLocation("232323", "445454");
        var expectedResult = geoLocationDto;
        GeoLocationStorage.getInstance().save(geoLocation, "id");

        var result = this.homeService.getGeoLocationById("id", "token");

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetGeoLocationByIdWithInvalidId() {
        GeoLocation geoLocation = new GeoLocation("232323", "445454");
        GeoLocationStorage.getInstance().save(geoLocation, "id");

        assertThrows(NotFoundException.class, () -> this.homeService.getGeoLocationById("wrongId", "token"));
    }

    @Test
    void testGetGeoLocationByIdWithValidToken() {
        GeoLocation geoLocation = new GeoLocation("232323", "445454");
        var expectedResult = geoLocationDto;
        GeoLocationStorage.getInstance().save(geoLocation, "id");

        var result = this.homeService.getGeoLocationById("id", "token");

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetGeoLocationByIdWithInvalidToken() {
        GeoLocation geoLocation = new GeoLocation("232323", "445454");
        GeoLocationStorage.getInstance().save(geoLocation, "id");

        assertThrows(UnauthorizedException.class, () -> this.homeService.getGeoLocationById("id", "wrongToken"));
    }
}