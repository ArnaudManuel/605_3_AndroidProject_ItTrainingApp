package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.OneStudent;
import com.cours644_1.maa_bom.ittrainingapp.coursView.OneCours;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.OneTeacher;

import java.util.Locale;

public class MainActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Button manageStudentBtn = (Button)findViewById(R.id.act_home_student_button);
        manageStudentBtn.setOnClickListener(new ManageStudentAction());

        Button manageTeacherBtn= (Button)findViewById(R.id.act_home_teacher_button);
        manageTeacherBtn.setOnClickListener(new ManageTeachertAction());

        Button manageCoursBtn =(Button)findViewById(R.id.act_home_cours_button);
        manageCoursBtn.setOnClickListener(new  ManageCoursAction());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mod_supp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }




    private class ManageStudentAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), OneStudent.class));
        }
    }
    private class ManageTeachertAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), OneTeacher.class));
        }
    }
    private class ManageCoursAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), OneCours.class));
        }
    }



}
