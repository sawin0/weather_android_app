package com.sabin.weather;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Weather {
    String imageUrl;
    String day;
    String status;
    String maxTemp;
    String minTemp;


    public Weather(String imageUrl, String day, String status, String maxTemp, String minTemp) {
        this.imageUrl = "https://openweathermap.org/img/wn/"+ imageUrl + "@2x.png";
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(day)), ZoneId.systemDefault());
        // Define a custom date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMM HH:mm");

        // Format the LocalDateTime using the formatter
        this.day = date.format(formatter);
        this.status = status;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
}

//public class Rain{
//    @JsonProperty("3h")
//    public double _3h;
//}

