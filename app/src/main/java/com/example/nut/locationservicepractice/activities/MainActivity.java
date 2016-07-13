package com.example.nut.locationservicepractice.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.nut.locationservicepractice.R;
import com.example.nut.locationservicepractice.services.LocationService;

public class MainActivity extends AppCompatActivity {

    private static final short ACCESS_COARSE_LOCATION_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationService.getInstance();
    }

    @Override
    protected void onStart() {
        permissionChecking();
        super.onStart();
    }

    private void permissionChecking() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            )) {

            } else {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        ACCESS_COARSE_LOCATION_CODE
                );
            }
        } else {
            LocationService.getInstance().getGoogleApiClient().connect();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ACCESS_COARSE_LOCATION_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationService.getInstance().getGoogleApiClient().connect();
                }
                return;
            }
        }
    }

    @Override
    protected void onStop() {
        if (LocationService.getInstance().getGoogleApiClient() != null
                && LocationService.getInstance().getGoogleApiClient().isConnected()) {
            LocationService.getInstance().getGoogleApiClient().disconnect();
        }
        super.onStop();
    }
}
