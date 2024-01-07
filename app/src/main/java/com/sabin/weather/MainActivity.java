package com.sabin.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    }
}