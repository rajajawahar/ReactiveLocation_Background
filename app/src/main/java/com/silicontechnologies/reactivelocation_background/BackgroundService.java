package com.silicontechnologies.reactivelocation_background;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by rajajawahar on 21/04/17.
 */

public class BackgroundService extends IntentService {

    public BackgroundService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }


}
