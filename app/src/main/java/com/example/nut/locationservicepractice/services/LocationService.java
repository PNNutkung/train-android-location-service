package com.example.nut.locationservicepractice.services;

import com.example.nut.locationservicepractice.applications.Contexter;
import com.example.nut.locationservicepractice.callbacks.GoogleCallbackStatus;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by nut on 7/13/16.
 */
public class LocationService {
    public static LocationService locationService;
    private GoogleApiClient googleApiClient;

    private LocationService() {
        googleApiClient = new GoogleApiClient.Builder(Contexter.getInstance().getApplicationContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(GoogleCallbackStatus.getInstance())
                .addOnConnectionFailedListener(GoogleCallbackStatus.getInstance())
                .build();
    }

    public static LocationService getInstance() {
        if(locationService == null) {
            locationService = new LocationService();
        }
        return locationService;
    }

    public GoogleApiClient getGoogleApiClient() {
        return this.googleApiClient;
    }
}
