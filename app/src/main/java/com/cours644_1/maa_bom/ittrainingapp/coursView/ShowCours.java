package com.cours644_1.maa_bom.ittrainingapp.coursView;

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

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
import com.cours644_1.maa_bom.ittrainingapp.sessionView.SessionsAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by arnaud on 20.11.2015.
 */
public class ShowCours extends Activity {
    private Cours cours;
    private TextView nameTxt;
    private TextView descriptionTxt;
    private Button modifyButton;
    private ListView sessionsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cours_show);


        cours= DataGeneralStore.store.getCoursById(getIntent().getExtras().getInt("coursId"));

        nameTxt=(TextView)findViewById(R.id.act_cours_show_nameTxt);
        descriptionTxt = (TextView)findViewById(R.id.act_cours_show_descriptionTxt);
        modifyButton = (Button) findViewById(R.id.act_cours_show_modifyButton);
        sessionsListView = (ListView) findViewById(R.id.act_cours_show_sessions_list);


        nameTxt.setText(cours.getName());
        descriptionTxt.setText(cours.getDescription());
        modifyButton.setOnClickListener(new ModifyCoursAction());


        List<Session> temp =DataGeneralStore.store.getSessionFor(cours);
        Collections.sort(temp);
        Session[] sesions= new Session[temp.size()];
        temp.toArray(sesions);
        ArrayAdapter<Session> adapter = new SessionsAdapter(getApplicationContext(),sesions);
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
            Intent intent= new Intent(getApplicationContext(),ModifyCours.class);
            intent.putExtra("coursId",cours.getId());
            startActivity(intent);
        }
        if(id == R.id.action_delete){
            //// TODO !
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ModifyCoursAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyCours.class);
            intent.putExtra("coursId",cours.getId());
            startActivity(intent);
        }
    }

}