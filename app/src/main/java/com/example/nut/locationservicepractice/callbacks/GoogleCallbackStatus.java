package com.example.nut.locationservicepractice.callbacks;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
public class GoogleCallbackStatus implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

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
        if (ActivityCompat.checkSelfPermission(
                Contexter.getInstance().getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                Contexter.getInstance().getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {
            LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(LocationService.getInstance().getGoogleApiClient());
            if (locationAvailability.isLocationAvailable()) {
                LocationRequest request = new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_LOW_POWER)
                        .setInterval(Constants.LOCATION_REQUEST_TIME_INTERVAL)
                        .setExpirationDuration(Constants.LOCATION_REQUEST_EXPIRATION_DURATION)
                        .setExpirationTime(Constants.LOCATION_REQUEST_EXPIRATION_TIME)
                        .setFastestInterval(Constants.LOCATION_REQUEST_FASTEST_INTERVAL)
                        .setNumUpdates(Constants.LOCATION_REQUEST_NUM_UPDATE)
                        .setSmallestDisplacement(Constants.LOCATION_REQUEST_SMALLEST_DISPLACEMENT);
                LocationListener listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Toast.makeText(Contexter.getInstance().getApplicationContext(), String.format("Lat: %.3f, Long: %.3f", location.getLatitude(), location.getLongitude()), Toast.LENGTH_SHORT).show();
                    }
                };
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        LocationService.getInstance().getGoogleApiClient(),
                        request,
                        listener
                );
            } else {

            }
            return;
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
