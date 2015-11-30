package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.TeacherModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
import com.cours644_1.maa_bom.ittrainingapp.coursView.ShowCours;
import com.cours644_1.maa_bom.ittrainingapp.sessionView.SessionsAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by arnaud on 19.11.2015.
 */
public class ShowTeacher extends Activity {
    private Teacher teacher;
    private TextView nameTxt;
    private TextView firstnameTxt;
    private TextView mailTxt;
    private TextView descriptionTxt;
    private Button modifyButton;
    private ListView sessionsListView;
    private DataStore dataStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_teacher_show);
        dataStore= DataGeneralStore.getStore(getApplicationContext());


        nameTxt=(TextView)findViewById(R.id.act_teacher_show_nameTxt);
        firstnameTxt=(TextView)findViewById(R.id.act_teacher_show_firstnameTxt);
        mailTxt=(TextView)findViewById(R.id.act_teacher_show_mailTxt);
        descriptionTxt=(TextView)findViewById(R.id.act_teacher_show_descriptionTxt);
        modifyButton = (Button) findViewById(R.id.act_teacher_show_modifyButton);
        sessionsListView = (ListView)findViewById(R.id.act_teacher_show_sessions_list);



    }
@Override
protected void onResume(){
    super.onResume();
    teacher= dataStore.getTeacherById(getIntent().getExtras().getInt("personId"));
    if(false==teacher.isActive())
        finish();
    nameTxt.setText(teacher.getName());
    firstnameTxt.setText(teacher.getFirstname());
    mailTxt.setText(teacher.getMail());
    descriptionTxt.setText(teacher.getDescription());
    modifyButton.setOnClickListener(new ModifyStudentAction());



    List<Session> temp =dataStore.getSessionFor(teacher);
    Collections.sort(temp);
    Session[] sessions= new Session[temp.size()];
    temp.toArray(sessions);
    ArrayAdapter<Session> adapter = new SessionsAdapter(getApplicationContext(),sessions);
    sessionsListView.setAdapter(adapter);
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mod_supp, menu);
        menu.getItem(1).setVisible(true);
        menu.getItem(2).setVisible(true);
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
        if (id == R.id.action_modify) {
            Intent intent= new Intent(getApplicationContext(),ModifyTeacher.class);
            intent.putExtra("personId",teacher.getId());
            startActivity(intent);
        }
        if(id == R.id.action_delete){
            dataStore.delete(teacher);
            ShowTeacher.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private class ModifyStudentAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyTeacher.class);
            intent.putExtra("personId",teacher.getId());
            startActivity(intent);
        }
    }

}
