package com.example.nut.locationservicepractice.applications;

import android.content.Context;

/**
 * Created by nut on 7/13/16.
 */
public class Contexter {
    public static Contexter contexter;
    private Context context;

    private Contexter() {

    }

    public static Contexter getInstance() {
        if(contexter == null) {
            contexter = new Contexter();
        }
        return contexter;
    }

    public Context getApplicationContext() {
        return this.context;
    }

    public void setApplicationContext(Context context){
        this.context = context;
    }
}
