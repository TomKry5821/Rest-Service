package com.krypczyk.restservice.model;

import java.util.Objects;

public class GeoLocation {

    private String latitude;
    private String longitude;

    public GeoLocation() {
        this.latitude = "";
        this.longitude = "";
    }

    public GeoLocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
