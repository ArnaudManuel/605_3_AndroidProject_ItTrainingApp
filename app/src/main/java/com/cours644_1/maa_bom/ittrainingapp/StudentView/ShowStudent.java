package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;

/**
 * Created by arnaud on 14.11.2015.
 */
public class ShowStudent extends Activity {
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_student);

        student= DataGeneralStore.store.getStudentById(getIntent().getExtras().getInt("personId"));

        ((TextView)findViewById(R.id.nameTxt)).setText(student.getName());
        ((TextView)findViewById(R.id.firstnameTxt)).setText(student.getFirstname());
        ((TextView)findViewById(R.id.mailTxt)).setText(student.getMail());

        Button modifyButton = (Button) findViewById(R.id.show_student__modifyButton);
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
