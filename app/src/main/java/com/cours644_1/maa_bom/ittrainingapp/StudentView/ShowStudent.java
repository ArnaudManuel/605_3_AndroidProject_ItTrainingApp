package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.CustomActivity;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;
import com.cours644_1.maa_bom.ittrainingapp.coursView.CoursSelectorActivity;
import com.cours644_1.maa_bom.ittrainingapp.sessionView.SessionsAdapter;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ModifyTeacher;

import java.util.Collections;
import java.util.List;

/**
 * Created by arnaud on 14.11.2015.
 */
public final class ShowStudent extends CustomActivity {
    private Student student;
    private TextView nameTxt;
    private TextView firstnameTxt;
    private TextView mailTxt;
    private Button modifyButton;
    private ListView sessionsListView;
    private DataStore dataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_student_show);
        dataStore=DataGeneralStore.getStore(getApplicationContext());
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);


        nameTxt=(TextView)findViewById(R.id.act_student_show_nameTxt);
        firstnameTxt=(TextView)findViewById(R.id.act_student_show_firstnameTxt);
        mailTxt=(TextView)findViewById(R.id.act_student_show_mailTxt);
        modifyButton = (Button) findViewById(R.id.act_student_show_modifyButton);
        sessionsListView =(ListView) findViewById(R.id.act_student_show_sessions_list);

        }
    @Override
    protected void onResume(){
        super.onResume();
        student= dataStore.getStudentById(getIntent().getExtras().getInt("personId"));
        if(false==student.isActive())
            finish();


        nameTxt.setText(student.getName());
        firstnameTxt.setText(student.getFirstname());
        mailTxt.setText(student.getMail());
        modifyButton.setOnClickListener(new ModifyStudentAction());

        List<Session> temp =dataStore.getSessionFor(student);
        Collections.sort(temp);
        Session[] sessions= new Session[temp.size()];

        temp.toArray(sessions);
        ArrayAdapter<Session> adapter = new SessionsAdapter(getApplicationContext(),sessions);
        sessionsListView.setAdapter(adapter);

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
        if (id == R.id.action_modify) {
            Intent intent= new Intent(getApplicationContext(),ModifyTeacher.class);
            intent.putExtra("personId",student.getId());
            startActivity(intent);
        }
        if(id == R.id.action_delete){
            dataStore.delete(student);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private class ModifyStudentAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyStudent.class);
            intent.putExtra("personId", student.getId());
            startActivity(intent);
        }
    }


}
