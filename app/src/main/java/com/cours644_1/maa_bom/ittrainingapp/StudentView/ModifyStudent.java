package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.StudentModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;

public class ModifyStudent extends Activity {
    private StudentModificator student;
    private EditText nameTxtBx;
    private EditText firstnameTxtBx;
    private EditText mailTxtBx;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_student_modify);

        {//seting properties of the object for a clearer acces
            nameTxtBx = (EditText) findViewById(R.id.act_student_modify_nameTxtBx);
            firstnameTxtBx = (EditText) findViewById(R.id.act_student_modify_firstNameTxtBx);
            mailTxtBx = (EditText) findViewById(R.id.act_student_modify_mailTxtBx);
            saveButton = (Button) findViewById(R.id.act_student_modify_saveButton);

            //reaserchig of the proper student
            int studentId = getIntent().getExtras().getInt("personId", -1);
            if (studentId < 0)
                student = Student.newForCreation();
            else
                student = DataGeneralStore.store.getStudentById(studentId).getModificator();
        }
        {//seting default values in the editText & action listener on button
            if(student.getId()<0){
                //// TODO: 18.11.2015 put some localised context, and do not save default data
                nameTxtBx.setText("name");
                firstnameTxtBx.setText("firstName");
                mailTxtBx.setText("Adresse E-mail");
            }
            else{
                nameTxtBx.setText(student.getName());
                firstnameTxtBx.setText(student.getFirstname());
                mailTxtBx.setText(student.getMail());
            }
            saveButton.setOnClickListener(new SaveStudentAction());
        }
    }

    private class SaveStudentAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            String newName=nameTxtBx.getText().toString();
            String newFirstName=firstnameTxtBx.getText().toString();
            String newMail=mailTxtBx.getText().toString();

            if(!newName.equals(""))
                student.setName(newName);
            if(!newFirstName.equals(""))
                student.setFirstname(newFirstName);
            if(!newMail.equals(""))
                student.setMail(newMail);

            student.save();

            startActivity(new Intent(ModifyStudent.this.getApplicationContext(),OneStudent.class));
        }
    }
}
