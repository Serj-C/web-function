package com.example.webfunction;

public class TollStation {
    private String stationId;
    private Float mileMarker;
    private Integer stallCount;

    public TollStation(String stationId, Float mileMarker, Integer stallCount) {
        this.stationId = stationId;
        this.mileMarker = mileMarker;
        this.stallCount = stallCount;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Float getMileMarker() {
        return mileMarker;
    }

    public void setMileMarker(Float mileMarker) {
        this.mileMarker = mileMarker;
    }

    public Integer getStallCount() {
        return stallCount;
    }

    public void setStallCount(Integer stallCount) {
        this.stallCount = stallCount;
    }
}
