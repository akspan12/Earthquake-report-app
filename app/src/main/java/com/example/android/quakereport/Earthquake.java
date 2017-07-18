package com.example.android.quakereport;

/**
 * Created by AKSPAN12 on 09-07-2017.
 */

public class Earthquake {
    //variables
    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;

    //constructor
    public Earthquake(double mMagnitude, String mLocation, long mDate,String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mUrl=mUrl;
    }
    //getters
    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }
}
