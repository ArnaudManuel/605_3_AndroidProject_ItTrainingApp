package com.cours644_1.maa_bom.ittrainingapp.coursView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.R;
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

    private class ModifyCoursAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyCours.class);
            intent.putExtra("coursId",cours.getId());
            startActivity(intent);
        }
    }

}