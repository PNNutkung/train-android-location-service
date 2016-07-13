package com.example.nut.locationservicepractice.callbacks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by nut on 7/13/16.
 */
public class GoogleCallbackStatus implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static GoogleCallbackStatus googleCallbackStatus;

    private GoogleCallbackStatus() {

    }

    public static GoogleCallbackStatus getInstance() {
        if(googleCallbackStatus == null) {
            googleCallbackStatus = new GoogleCallbackStatus();
        }
        return googleCallbackStatus;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
