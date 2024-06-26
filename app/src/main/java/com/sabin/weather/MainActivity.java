package com.sabin.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    List<Weather> weatherListData = new ArrayList<>();
    private ProgressBar loadingSpinner;

    //TODO: change API key
    private final String API_KEY = "4e3152b2aefeafe6ddfa1495aceb550e";
    private final String url = "https://api.openweathermap.org/data/2.5/forecast?id=1282898&APPID=" + API_KEY + "&units=metric";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingSpinner = findViewById(R.id.loadingSpinner);

        loadingSpinner.setVisibility(View.VISIBLE);

        fetchWeather();

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd, MMM", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("formattedDate => " + formattedDate);

        TextView dateTV = findViewById(R.id.dateTV);
        dateTV.setText("Today " + formattedDate);


        // creating dummy weather data list
//        weatherList.add(new Weather("", "today", "clear", "70", "35"));
//        weatherList.add(new Weather("", "tomorrow", "clear", "70", "35"));
//        weatherList.add(new Weather("", "3rd day", "clear", "70", "35"));
//        weatherList.add(new Weather("", "today", "clear", "70", "35"));
//        weatherList.add(new Weather("", "today", "clear", "70", "35"));
    }


    void fetchWeather() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: " + response.substring(0,500));
                        JSONObject responseObject = null;
                        try {
                            responseObject = new JSONObject(response);
                            JSONArray weatherList = responseObject.getJSONArray("list");
                            Log.d("TAG", "onResponse: weatherList: " + weatherList.toString());
                            Log.d("TAG", "onResponse: hello");
                            // Iterate through the weather list
                            for (int i = 0; i < weatherList.length(); i++) {
                                JSONObject weatherObject = weatherList.getJSONObject(i);

                                // Extract relevant information for each entry
                                String dateTime = weatherObject.getString("dt");
                                String temMin = weatherObject.getJSONObject("main").getString("temp_min");
                                String temMax = weatherObject.getJSONObject("main").getString("temp_max");
                                String status = weatherObject.getJSONArray("weather").getJSONObject(0).getString("main");
                                String imageUrl = weatherObject.getJSONArray("weather").getJSONObject(0).getString("icon");

                                // adding data to weatherList
                                weatherListData.add(new Weather(imageUrl, dateTime, status, temMax, temMin));


                                // Handle the extracted information as needed
                                // You can update your UI or perform other actions here
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        setUpData();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fetchWeather", "That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void setUpData() {

        ImageView tempIV = findViewById(R.id.tempIV);
        Glide.with(getApplicationContext()).load(weatherListData.get(0).imageUrl).into(tempIV);

        TextView maxTempTV = findViewById(R.id.maxTempTV);
        TextView minTemTV = findViewById(R.id.minTempTV);

        maxTempTV.setText(weatherListData.get(0).maxTemp + "°");
        minTemTV.setText(weatherListData.get(0).minTemp + "°");

        // removing first weather data
        weatherListData.remove(0);
        setUpRV();
    }

    void setUpRV() {
        // Lookup the recyclerview in activity layout
        RecyclerView weatherRV = findViewById(R.id.weatherRV);

        // Initialize contacts
//        contacts = Weather.createContactsList(20);
        // Create adapter passing in the sample user data
        WeatherAdapter adapter = new WeatherAdapter(weatherListData, this);
        // Attach the adapter to the recyclerview to populate items
        weatherRV.setAdapter(adapter);
        // Set layout manager to position the items
        weatherRV.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        // hiding progressbar
        loadingSpinner.setVisibility(View.GONE);
    }
}