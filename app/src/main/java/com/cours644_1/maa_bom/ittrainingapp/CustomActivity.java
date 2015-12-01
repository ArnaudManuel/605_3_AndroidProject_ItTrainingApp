package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by arnaud on 01.12.2015.
 */
public abstract class CustomActivity extends Activity {

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        changeLanguage(sharedPrefs.getString("pref_lang", "fr"));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void changeLanguage(String lang){
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

}
