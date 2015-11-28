package com.cours644_1.maa_bom.ittrainingapp.coursView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by arnaud on 20.11.2015.
 */
public class ModifyCours extends Activity {
    private CoursModificator cours;
    private EditText nameTxtBx;
    private EditText descriptionTxtBx;
    private Button saveButton;
    private Button newSessionButton;
    private DataStore dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cours_modify);

        {//seting properties of the object for a clearer acces
            nameTxtBx = (EditText) findViewById(R.id.act_cours_modify_nameTxtBx);
            descriptionTxtBx = (EditText) findViewById(R.id.act_cours_modify_descTxtBx);
            saveButton = (Button) findViewById(R.id.act_cours_modify_saveButton);
            newSessionButton = (Button) findViewById(R.id.act_cours_modify_createNewSessonButton);
            dataStore= DataGeneralStore.getStore(getApplicationContext());


            //reaserchig of the proper student
            int coursId = getIntent().getExtras().getInt("coursId", -1);
            if (coursId < 0)
                cours = Cours.newForCreation();
            else
                cours = dataStore.getCoursById(coursId).getModificator();
        }
        {//seting default values in the editText & action listener on button
            if(cours.getId()<0){
                //// TODO: 18.11.2015 put some localised context, and do not save default data
                nameTxtBx.setText("name");
                descriptionTxtBx.setText("description");

            }
            else{
                nameTxtBx.setText(cours.getName());
                descriptionTxtBx.setText(cours.getDescription());
            }
            saveButton.setOnClickListener(new SaveCoursAction());
            newSessionButton.setOnClickListener(new NewSessionAction());
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

            dataStore.save(cours);

            startActivity(new Intent(ModifyCours.this.getApplicationContext(), OneCours.class));
        }
    }
    private  class NewSessionAction implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ModifySession.class);
            intent.putExtra("coursId", cours.getId());
            intent.putExtra("sessionId",-1);
            startActivity(intent);
        }
    }
}
