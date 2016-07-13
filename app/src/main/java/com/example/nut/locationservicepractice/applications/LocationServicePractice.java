package com.example.nut.locationservicepractice.applications;

import android.app.Application;

/**
 * Created by nut on 7/13/16.
 */
public class LocationServicePractice extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contexter.getInstance().setApplicationContext(this);
    }
}
