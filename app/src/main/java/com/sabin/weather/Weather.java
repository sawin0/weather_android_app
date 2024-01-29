package com.sabin.weather;

import java.util.ArrayList;

public class Weather {
    String imageUrl;
    String day;
    String status;
    String maxTemp;
    String minTemp;


    public Weather(String imageUrl, String day, String status, String maxTemp, String minTemp) {
        this.imageUrl = "https://openweathermap.org/img/wn/"+ imageUrl + "@2x.png";
        this.day = day;
        this.status = status;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
}

//public class Rain{
//    @JsonProperty("3h")
//    public double _3h;
//}

