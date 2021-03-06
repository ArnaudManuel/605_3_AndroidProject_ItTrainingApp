package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cours644_1.maa_bom.ittrainingapp.CustomActivity;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.StudentModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;
import com.cours644_1.maa_bom.ittrainingapp.coursView.CoursSelectorActivity;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.OneTeacher;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ShowTeacher;

public class ModifyStudent extends CustomActivity {
    private StudentModificator student;
    private EditText nameTxtBx;
    private EditText firstnameTxtBx;
    private EditText mailTxtBx;
    private Button saveButton;
    private Button deleteButton;
    private Button manageCours;
    private DataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_student_modify);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //seting properties of the object for a clearer acces
        nameTxtBx = (EditText) findViewById(R.id.act_student_modify_nameTxtBx);
        firstnameTxtBx = (EditText) findViewById(R.id.act_student_modify_firstNameTxtBx);
        mailTxtBx = (EditText) findViewById(R.id.act_student_modify_mailTxtBx);
        saveButton = (Button) findViewById(R.id.act_student_modify_saveButton);
        dataStore=DataGeneralStore.getStore(getApplicationContext());
        manageCours = (Button) findViewById(R.id.act_student_modify_manageSessionButton);
        manageCours.setOnClickListener(new ManageCoursAction());
        deleteButton = (Button) findViewById(R.id.act_student_modify_deleteButton);


            //reaserchig of the proper student

        saveButton.setOnClickListener(new SaveStudentAction());

    }
    @Override
    protected void onResume(){
        super.onResume();
        int studentId = getIntent().getExtras().getInt("personId", -1);
        if (studentId < 0) {
            student = Student.newForCreation();
            deleteButton.setVisibility(View.INVISIBLE);
            manageCours.setVisibility(View.INVISIBLE);
        }
        else {
            student = dataStore.getStudentById(studentId).getModificator();
            nameTxtBx.setText(student.getName());
            firstnameTxtBx.setText(student.getFirstname());
            mailTxtBx.setText(student.getMail());
            manageCours.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new DeleteAction());
        }
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
        if (id == R.id.action_settings) {/**
         * Created by arnaud on 19.11.2015.
         */
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private class SaveStudentAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String newName=nameTxtBx.getText().toString();
            String newFirstName=firstnameTxtBx.getText().toString();
            String newMail=mailTxtBx.getText().toString();
                student.setName(newName);
                student.setFirstname(newFirstName);
                student.setMail(newMail);
            int newId =dataStore.save(student);

            if(student.getId()<0){
                Intent intent = new Intent(ModifyStudent.this.getApplicationContext(),ShowStudent.class);
                intent.putExtra("personId",newId);
                startActivity(intent);
            }
            finish();
        }
    }
    private class ManageCoursAction implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), CoursSelectorActivity.class);
            intent.putExtra("personId", student.getId());
            startActivity(intent);
        }
    }
    private class DeleteAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            dataStore.delete(student);
            finish();

        }
    }

}
