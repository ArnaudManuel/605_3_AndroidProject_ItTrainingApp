package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.R;

/**
 * Created by arnaud on 14.11.2015.
 */
public final class ShowStudent extends Activity {
    private Student student;
    private TextView nameTxt;
    private TextView firstnameTxt;
    private TextView mailTxt;
    private Button modifyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_student_show);


        student= DataGeneralStore.store.getStudentById(getIntent().getExtras().getInt("personId"));
        nameTxt=(TextView)findViewById(R.id.act_student_show_nameTxt);
        firstnameTxt=(TextView)findViewById(R.id.act_student_show_firstnameTxt);
        mailTxt=(TextView)findViewById(R.id.act_student_show_mailTxt);
        modifyButton = (Button) findViewById(R.id.act_student_show_modifyButton);


        nameTxt.setText(student.getName());
        firstnameTxt.setText(student.getFirstname());
        mailTxt.setText(student.getMail());
        modifyButton.setOnClickListener(new ModifyStudentAction());
    }

    private class ModifyStudentAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyStudent.class);
            intent.putExtra("personId",student.getId());
            startActivity(intent);
        }
    }
}
