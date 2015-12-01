package com.cours644_1.maa_bom.ittrainingapp.coursView;

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

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.CoursModificator;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.StudentModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.OneStudent;
import com.cours644_1.maa_bom.ittrainingapp.sessionView.ModifySession;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.MultipleTeacher;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.OneTeacher;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ShowTeacher;

/**
 * Created by arnaud on 20.11.2015.
 */
public class ModifyCours extends Activity {
    private CoursModificator cours;
    private EditText nameTxtBx;
    private EditText descriptionTxtBx;
    private Button saveButton;
    private Button deleteButton;
    private Button atributeTeacher;
    private DataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cours_modify);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        {//seting properties of the object for a clearer acces
            nameTxtBx = (EditText) findViewById(R.id.act_cours_modify_nameTxtBx);
            descriptionTxtBx = (EditText) findViewById(R.id.act_cours_modify_descTxtBx);
            saveButton = (Button) findViewById(R.id.act_cours_modify_saveButton);
            dataStore= DataGeneralStore.getStore(getApplicationContext());
            atributeTeacher = (Button) findViewById(R.id.act_cours_modify_atributeTeacherButton);
            atributeTeacher.setOnClickListener(new AtributeTeacherAction());
            saveButton.setOnClickListener(new SaveCoursAction());
            deleteButton = (Button) findViewById(R.id.act_cours_modify_deleteButton);


            //reaserchig of the proper student
            int coursId = getIntent().getExtras().getInt("coursId", -1);
            if (coursId < 0) {
                cours = Cours.newForCreation();
                deleteButton.setVisibility(View.INVISIBLE);
            }
            else {
                cours = dataStore.getCoursById(coursId).getModificator();
                deleteButton.setVisibility(View.VISIBLE);
                deleteButton.setOnClickListener(new DeleteAction());
            }



        }
        {//seting default values in the editText & action listener on button
            if(cours.getId()<0){
                //// TODO: 18.11.2015 put some localised context, and do not save default data
                atributeTeacher.setVisibility(View.INVISIBLE);
            }
            else{
                nameTxtBx.setText(cours.getName());
                descriptionTxtBx.setText(cours.getDescription());
                atributeTeacher.setVisibility(View.VISIBLE);
            }

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

    private class SaveCoursAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            String newName=nameTxtBx.getText().toString();
           String newDescription=descriptionTxtBx.getText().toString();

            if(!newName.equals(""))
                cours.setName(newName);
            if(!newDescription.equals(""))
                cours.setDescription(newDescription);

            int newId = dataStore.save(cours);

            if(cours.getId()<0){
                Intent intent = new Intent(ModifyCours.this.getApplicationContext(),ShowCours.class);
                intent.putExtra("coursId", newId);
                startActivity(intent);
            }

            finish();
        }
    }

    private class AtributeTeacherAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ModifyCours.this.getApplicationContext(),MultipleTeacher.class);
            intent.putExtra("coursId", cours.getId());
            startActivity(intent);
        }
    }
    private class DeleteAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            dataStore.delete(cours);
            finish();
        }
    }


}
