package com.krypczyk.restservice.dto;

import java.util.Objects;

public class GeoLocationDto {
    private String deviceId;
    private String latitude;
    private String longitude;

    public GeoLocationDto(String deviceId, String latitude, String longitude) {
        this.deviceId = deviceId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getLatitude() {
        return latitude;
    }


    public String getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoLocationDto)) return false;
        GeoLocationDto that = (GeoLocationDto) o;
        return deviceId.equals(that.deviceId) && latitude.equals(that.latitude) && longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, latitude, longitude);
    }
}
