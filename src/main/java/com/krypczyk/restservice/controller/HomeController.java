package com.krypczyk.restservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.krypczyk.restservice.Exception.EmptyParameterException;
import com.krypczyk.restservice.Exception.InvalidDataException;
import com.krypczyk.restservice.Exception.NotFoundException;
import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.dto.GeoLocationDto;
import com.krypczyk.restservice.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @PostMapping("/geolocation")
    public String saveGeolocationData(@RequestBody GeoLocationDto geoLocationDto, @RequestHeader("accessToken") String accessToken) throws UnauthorizedException {
        this.homeService.saveGeolocation(geoLocationDto, accessToken);
        return "Data saved successfully!";
    }

    @GetMapping("/geolocation")
    public List<GeoLocationDto> getAllGeolocations(@RequestHeader("accessToken") String accessToken) throws UnauthorizedException {
        return this.homeService.getAllGeoLocations(accessToken);
    }

    @GetMapping("/geolocation/{deviceId}")
    public GeoLocationDto getGeoLocationById(@PathVariable("deviceId") String deviceId, @RequestHeader("accessToken") String accessToken) throws EmptyParameterException, NotFoundException, UnauthorizedException {
        return this.homeService.getGeoLocationById(deviceId, accessToken);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(InvalidFormatException e) {
        return "Provided data are invalid";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(EmptyParameterException emptyParameterException) {
        return emptyParameterException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String handleException(UnauthorizedException unauthorizedException) {
        return unauthorizedException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(InvalidDataException invalidDataException) {
        return invalidDataException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(JsonParseException jsonParseException) {
        return "Invalid data format";
    }
}
