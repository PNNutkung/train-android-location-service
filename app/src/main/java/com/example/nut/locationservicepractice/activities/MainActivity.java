package com.example.nut.locationservicepractice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nut.locationservicepractice.R;
import com.example.nut.locationservicepractice.services.LocationService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocationService.getInstance().getGoogleApiClient().connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(LocationService.getInstance().getGoogleApiClient().isConnected()) {
            LocationService.getInstance().getGoogleApiClient().disconnect();
        }
    }
}
