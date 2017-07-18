package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by AKSPAN12 on 09-07-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create a view
        View listItemView=convertView;
        //check for existing view,otherwise inflate a new layout
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }
        //find the earthquake at the given position in the list
        Earthquake currentEarthquake=getItem(position);




        //calling format method
        String magnitude=formatMagnitude(currentEarthquake.getmMagnitude());
        //find textView with id magnitude
        TextView magnitudeView=(TextView) listItemView.findViewById(R.id.magnitude);
        //Display the magnitude of current earthquake in that textView
        magnitudeView.setText(magnitude);

        //setting color of circle for magnitude
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = currentEarthquake.getmLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);


        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getmDate());
        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        //return the listItemView set with appropriate date
        return listItemView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    //formate the double value of magnitude to string
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

   //get color
   private int getMagnitudeColor(double magnitude) {
       int magnitudeColorResourceId;
       int magnitudeFloor = (int) Math.floor(magnitude);
       switch (magnitudeFloor) {
           case 0:
           case 1:
               magnitudeColorResourceId = R.color.magnitude1;
               break;
           case 2:
               magnitudeColorResourceId = R.color.magnitude2;
               break;
           case 3:
               magnitudeColorResourceId = R.color.magnitude3;
               break;
           case 4:
               magnitudeColorResourceId = R.color.magnitude4;
               break;
           case 5:
               magnitudeColorResourceId = R.color.magnitude5;
               break;
           case 6:
               magnitudeColorResourceId = R.color.magnitude6;
               break;
           case 7:
               magnitudeColorResourceId = R.color.magnitude7;
               break;
           case 8:
               magnitudeColorResourceId = R.color.magnitude8;
               break;
           case 9:
               magnitudeColorResourceId = R.color.magnitude9;
               break;
           default:
               magnitudeColorResourceId = R.color.magnitude10plus;
               break;
       }
       return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
   }

}
