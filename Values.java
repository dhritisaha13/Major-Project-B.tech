package com.example.loginapp;

public class Values {
    String id;
    String name;
    String location;
    double xValue,yValue,zValue,xgyroValue,ygyroValue,zgyroValue,latitude,longitude;

    public Values(){

    }

    public Values(String id, String name, String location, double xValue, double yValue, double zValue, double xgyroValue, double ygyroValue, double zgyroValue, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;
        this.xgyroValue = xgyroValue;
        this.ygyroValue = ygyroValue;
        this.zgyroValue = zgyroValue;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getxValue() {
        return xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public double getzValue() {
        return zValue;
    }

    public double getXgyroValue() {
        return xgyroValue;
    }

    public double getYgyroValue() {
        return ygyroValue;
    }

    public double getZgyroValue() {
        return zgyroValue;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
