package com.silicontechnologies.reactivelocation_background;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.LocationRequest;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by rajajawahar on 21/04/17.
 */

public class BackgroundService extends IntentService {

    private ReactiveLocationProvider locationProvider;
    private Observable<Location> locationUpdatesObservable;
    private LocationRequest locationRequest;
    public static final String TAG = BackgroundService.class.getSimpleName();


    public BackgroundService() {
        super("LOCATION_SERVICE");
        locationProvider = new ReactiveLocationProvider(this);
        Log.d(TAG, "Constructor Called");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(500);

        Log.d(TAG, "onCreate Called");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent Called");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
//        locationProvider.getLastKnownLocation().subscribe(new Action1<Location>() {
//            @Override
//            public void call(Location location) {
//                Log.d(TAG, "call: " + location.toString());
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                Log.d(TAG, "call: " + throwable.toString());
//            }
//        });
        locationProvider.getUpdatedLocation(locationRequest).
                subscribe(new Action1<Location>() {
                    @Override
                    public void call(Location location) {
                        Log.d(TAG, "call: " + location.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "call: " + throwable.getLocalizedMessage());
                    }
                })
        ;
    }


}
