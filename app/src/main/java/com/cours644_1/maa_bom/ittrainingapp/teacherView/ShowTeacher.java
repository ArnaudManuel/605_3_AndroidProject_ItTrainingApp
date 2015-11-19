package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.TeacherModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_teacher_show);


        teacher= DataGeneralStore.store.getTeacherById(getIntent().getExtras().getInt("personId"));
        nameTxt=(TextView)findViewById(R.id.act_teacher_show_nameTxt);
        firstnameTxt=(TextView)findViewById(R.id.act_teacher_show_firstnameTxt);
        mailTxt=(TextView)findViewById(R.id.act_teacher_show_mailTxt);
        descriptionTxt=(TextView)findViewById(R.id.act_teacher_show_descriptionTxt);
        modifyButton = (Button) findViewById(R.id.act_teacher_show_modifyButton);


        nameTxt.setText(teacher.getName());
        firstnameTxt.setText(teacher.getFirstname());
        mailTxt.setText(teacher.getMail());

        modifyButton.setOnClickListener(new ModifyStudentAction());
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
