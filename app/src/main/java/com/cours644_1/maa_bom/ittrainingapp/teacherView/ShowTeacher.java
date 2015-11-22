package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.TeacherModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_teacher_show);


        teacher= DataGeneralStore.store.getTeacherById(getIntent().getExtras().getInt("personId"));
        nameTxt=(TextView)findViewById(R.id.act_teacher_show_nameTxt);
        firstnameTxt=(TextView)findViewById(R.id.act_teacher_show_firstnameTxt);
        mailTxt=(TextView)findViewById(R.id.act_teacher_show_mailTxt);
        descriptionTxt=(TextView)findViewById(R.id.act_teacher_show_descriptionTxt);
        modifyButton = (Button) findViewById(R.id.act_teacher_show_modifyButton);
        sessionsListView = (ListView)findViewById(R.id.act_teacher_show_sessions_list);


        nameTxt.setText(teacher.getName());
        firstnameTxt.setText(teacher.getFirstname());
        mailTxt.setText(teacher.getMail());
        descriptionTxt.setText(teacher.getDescription());
        modifyButton.setOnClickListener(new ModifyStudentAction());



        List<Session> temp =DataGeneralStore.store.getSessionFor(teacher);
        Collections.sort(temp);
        Session[] sessions= new Session[temp.size()];
        temp.toArray(sessions);
        ArrayAdapter<Session> adapter = new SessionsAdapter(getApplicationContext(),sessions);
        sessionsListView.setAdapter(adapter);
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
