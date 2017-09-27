package com.example.android.quakereport;

/**
 * Created by eabac on 9/25/2017.
 */

public class Earthquake {

    private double mMagnitude;
    private String mOriginalLocation;
    private long mTimeInMilliseconds;
    private String mUrl;


    public Earthquake(double magnitude, String originalLocation, long timeInMilliseconds, String url)
    {
        mMagnitude = magnitude;
        mOriginalLocation= originalLocation;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public String getOriginalLocation() { return mOriginalLocation; }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getTimeInMilliseconds() { return mTimeInMilliseconds; }

    public String getUrl() { return mUrl; }


}
