package com.example.nut.locationservicepractice.callbacks;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.nut.locationservicepractice.applications.Contexter;
import com.example.nut.locationservicepractice.informations.Constants;
import com.example.nut.locationservicepractice.services.LocationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by nut on 7/13/16.
 */
public class GoogleCallbackStatus implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static GoogleCallbackStatus googleCallbackStatus;

    private GoogleCallbackStatus() {

    }

    public static GoogleCallbackStatus getInstance() {
        if (googleCallbackStatus == null) {
            googleCallbackStatus = new GoogleCallbackStatus();
        }
        return googleCallbackStatus;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(Contexter.getInstance().getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(LocationService.getInstance().getGoogleApiClient());
        if (locationAvailability.isLocationAvailable()) {
            LocationRequest request = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(Constants.LOCATION_REQUEST_TIME_INTERVAL)
                    .setFastestInterval(Constants.LOCATION_REQUEST_FASTEST_INTERVAL);
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    LocationService.getInstance().getGoogleApiClient(),
                    request,
                    this
            );
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        showLocationLatLong(location);
    }

    public void showLocationLatLong(Location location) {
        String latLong = String.format("Lat: %.7f, Long: %.7f", location.getLatitude(), location.getLongitude());
        Log.e("Location", latLong);
        Toast.makeText(Contexter.getInstance().getApplicationContext(), latLong, Toast.LENGTH_SHORT).show();
    }
}
