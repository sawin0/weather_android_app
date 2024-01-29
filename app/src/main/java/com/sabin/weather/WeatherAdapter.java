package com.sabin.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class WeatherAdapter extends
        RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Weather> weatherList;
    private Context context;

    // Pass in the contact array into the constructor
    public WeatherAdapter(List<Weather> weathers, Context mContext) {
        weatherList = weathers;
        context = mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View weatherView = inflater.inflate(R.layout.item_weather, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(weatherView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Weather weather = weatherList.get(position);

        // Set item views based on your views and data model
        ImageView tempIV = holder.tempIV;
        Glide.with(context).load(weather.imageUrl).into(tempIV);

        //TODO: load image using Glide
        TextView dateTV = holder.dateTV;
        dateTV.setText(weather.day);
        TextView tempStatusTV = holder.tempStatusTV;
        tempStatusTV.setText(weather.status);
        TextView maxTempTV = holder.maxTempTV;
        maxTempTV.setText(weather.maxTemp);
        TextView minTempTV = holder.minTempTV;
        minTempTV.setText(weather.minTemp);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView tempIV;
        public TextView dateTV;
        public TextView tempStatusTV;
        public TextView maxTempTV;
        public TextView minTempTV;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tempIV = itemView.findViewById(R.id.tempIV);
            dateTV = itemView.findViewById(R.id.dateTV);
            tempStatusTV = itemView.findViewById(R.id.tempStatusTV);
            maxTempTV = itemView.findViewById(R.id.maxTempTV);
            minTempTV = itemView.findViewById(R.id.minTempTV);

//            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
//            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}