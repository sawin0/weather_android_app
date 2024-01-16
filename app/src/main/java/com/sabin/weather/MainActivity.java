package com.sabin.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd, MMM", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("formattedDate => " + formattedDate);

        TextView dateTV = findViewById(R.id.dateTV);
        dateTV.setText("Today "+formattedDate);

        ImageView tempIV = findViewById(R.id.tempIV);
        Glide.with(this).load("https://goo.gl/gEgYUd").into(tempIV);


        // creating dummy weather data list
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(new Weather("", "today", "clear", "70", "35"));
        weatherList.add(new Weather("", "tomorrow", "clear", "70", "35"));
        weatherList.add(new Weather("", "3rd day", "clear", "70", "35"));
        weatherList.add(new Weather("", "today", "clear", "70", "35"));
        weatherList.add(new Weather("", "today", "clear", "70", "35"));





        // Lookup the recyclerview in activity layout
        RecyclerView weatherRV = (RecyclerView) findViewById(R.id.weatherRV);

        // Initialize contacts
//        contacts = Weather.createContactsList(20);
        // Create adapter passing in the sample user data
        WeatherAdapter adapter = new WeatherAdapter(weatherList);
        // Attach the adapter to the recyclerview to populate items
        weatherRV.setAdapter(adapter);
        // Set layout manager to position the items
        weatherRV.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }
}