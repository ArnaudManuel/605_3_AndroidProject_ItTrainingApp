package com.cours644_1.maa_bom.ittrainingapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Maximilien on 19.11.2015.
 * Activité pour les settings de l'application
 * Pour le moment le seul paramètre implémenté c'est la langue
 */
public class SettingsActivity extends CustomActivity{
    private final String lang = "fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);



        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,new SettingsFragment())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.p, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml 7
        return super.onOptionsItemSelected(item);
    }






}
