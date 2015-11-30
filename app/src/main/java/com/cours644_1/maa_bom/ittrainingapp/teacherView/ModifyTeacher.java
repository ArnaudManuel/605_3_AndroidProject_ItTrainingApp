package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.StudentModificator;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.TeacherModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;

/**
 * Created by arnaud on 19.11.2015.
 */
public class ModifyTeacher extends Activity {
    private TeacherModificator teacher;
    private EditText nameTxtBx;
    private EditText firstnameTxtBx;
    private EditText mailTxtBx;
    private EditText descriptionTxtBx;
    private Button saveButton;
    private Button deleteButton;
    private DataStore dataStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_teacher_modify);
        dataStore= DataGeneralStore.getStore(getApplicationContext());

        {//seting properties of the object for a clearer acces
            nameTxtBx = (EditText) findViewById(R.id.act_teacher_modify_nameTxtBx);
            firstnameTxtBx = (EditText) findViewById(R.id.act_teacher_modify_firstNameTxtBx);
            mailTxtBx = (EditText) findViewById(R.id.act_teacher_modify_mailTxtBx);
            descriptionTxtBx=(EditText)findViewById(R.id.act_teacher_modify_descTxtBx);
            saveButton = (Button) findViewById(R.id.act_teacher_modify_saveButton);
            deleteButton = (Button) findViewById(R.id.act_teacher_modify_deleteButton);

            //reaserchig of the proper student
            int teacherId = getIntent().getExtras().getInt("personId", -1);
            if (teacherId < 0) {
                teacher = teacher.newForCreation();
                deleteButton.setVisibility(View.INVISIBLE);
            }
            else {
                teacher = dataStore.getTeacherById(teacherId).getModificator();
                deleteButton.setVisibility(View.VISIBLE);
                deleteButton.setOnClickListener(new DeleteAction());
            }
        }
        {//seting default values in the editText & action listener on button
            if(teacher.getId()<0){
                //// TODO: 18.11.2015 put some localised context, and do not save default data
                nameTxtBx.setText("name");
                firstnameTxtBx.setText("firstName");
                mailTxtBx.setText("Adresse E-mail");
                descriptionTxtBx.setText("Description");
            }
            else{
                nameTxtBx.setText(teacher.getName());
                firstnameTxtBx.setText(teacher.getFirstname());
                mailTxtBx.setText(teacher.getMail());
                descriptionTxtBx.setText(teacher.getDescription());
            }
            saveButton.setOnClickListener(new SaveTeacherAction());
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

    private class SaveTeacherAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            String newName=nameTxtBx.getText().toString();
            String newFirstName=firstnameTxtBx.getText().toString();
            String newMail=mailTxtBx.getText().toString();
            String newDescription=descriptionTxtBx.getText().toString();

            if(!newName.equals(""))
                teacher.setName(newName);
            if(!newFirstName.equals(""))
                teacher.setFirstname(newFirstName);
            if(!newMail.equals(""))
                teacher.setMail(newMail);
            if(!newDescription.equals(""))
                teacher.setDescription(newDescription);

            int newId =dataStore.save(teacher);
            Intent intent;
            if(teacher.getId()<0){
                intent = new Intent(ModifyTeacher.this.getApplicationContext(),ShowTeacher.class);
                intent.putExtra("personId",newId);
                startActivity(intent);
            }

            finish();
        }
    }
    private class DeleteAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            dataStore.delete(teacher);
            finish();
        }
    }

}