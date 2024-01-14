package com.sabin.weather;

public class Weather {
    String imageUrl;
    String day;
    String status;
    String maxTemp;
    String minTemp;


    public Weather(String imageUrl, String day, String status, String maxTemp, String minTemp) {
        this.imageUrl = imageUrl;
        this.day = day;
        this.status = status;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
}
