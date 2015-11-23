package com.cours644_1.maa_bom.ittrainingapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cours644_1.maa_bom.ittrainingapp.Settings.Preferences;

/**
 * Created by Maximilien on 19.11.2015.
 */
public class SettingsActivity extends Activity{
    private final String lang = "fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);

        getTheme().applyStyle(new Preferences(this).getFontStyle().getResId(), true);

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
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (v.getId()){
            case R.id.change_theme_txt:
                CharSequence colors[] = new CharSequence[] {"red", "green", "blue", "black", "grey"};
                builder.setTitle("Choisir la couleur du thème");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActionBar bar = getActionBar();
                        bar.setBackgroundDrawable(new ColorDrawable(000000));
                    }
                });
                builder.show();
                break;
            case R.id.change_language_txt:
                CharSequence language[] = new CharSequence[] {"Français", "Anglais"};
                builder.setTitle("Choisir la langue");
                builder.setItems(language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                    }
                });
                builder.show();
                break;
            case R.id.change_police_txt:
                CharSequence taille[] = new CharSequence[] {"Petit", "Moyen", "Grand"};
                builder.setTitle("Choisir la taille de police");
                builder.setItems(taille, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                    }
                });
                builder.show();
                break;
        }



    }


}
