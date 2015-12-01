package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ModifyTeacher;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ShowTeacher;

import java.util.Locale;


public abstract class SelectionList extends CustomActivity {
    protected Object[] items;
    protected ArrayAdapter adapter;
    protected Button newItemButton;
    protected ListView list;
    protected DataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_list);
        dataStore= DataGeneralStore.getStore(getApplicationContext());

        list = (ListView) findViewById(R.id.selection_view);
        newItemButton = (Button) findViewById(R.id.slection_list__add_new_item_button);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);


    }

    protected abstract void specialise();


    @Override
    protected void onResume(){
        super.onResume();
        specialise();

        list.setAdapter(adapter);
    }




}
