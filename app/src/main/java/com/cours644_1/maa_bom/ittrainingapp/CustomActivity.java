package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.res.Configuration;

/**
 * Created by arnaud on 01.12.2015.
 */
public abstract class CustomActivity extends Activity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        newConfig.orientation=Configuration.ORIENTATION_PORTRAIT;
        super.onConfigurationChanged(newConfig);
    }
}
