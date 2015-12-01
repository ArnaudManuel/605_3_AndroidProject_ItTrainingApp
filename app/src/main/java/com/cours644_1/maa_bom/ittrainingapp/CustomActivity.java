package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

/**
 * Created by arnaud on 01.12.2015.
 */
public abstract class CustomActivity extends Activity {

    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        newConfig.orientation=Configuration.ORIENTATION_PORTRAIT;
        super.onConfigurationChanged(newConfig);
    }
}
