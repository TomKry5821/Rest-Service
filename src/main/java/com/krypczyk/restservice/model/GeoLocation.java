package com.krypczyk.restservice.model;

import java.util.Objects;

public class GeoLocation {

    private String deviceId;
    private String latitude;
    private String longitude;

    public GeoLocation() {
        this.deviceId = "";
        this.latitude = "";
        this.longitude = "";
    }

    public GeoLocation(String deviceId, String latitude, String longitude) {
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
        if (!(o instanceof GeoLocation that)) return false;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && deviceId.equals(that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, latitude, longitude);
    }
}
