package com.cours644_1.maa_bom.ittrainingapp.sessionView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.MainActivity;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;
import com.cours644_1.maa_bom.ittrainingapp.coursView.ShowCours;

import java.util.List;

/**
 * Created by arnaud on 23.11.2015.
 */
public class SessionsList extends SelectionList {
    private Cours cours;
    private Session[] sessions;


    @Override
    protected void specialise() {
        cours= dataStore.getCoursById(getIntent().getExtras().getInt("coursId"));

        List temp=dataStore.getAllSession(cours);
        sessions = new Session[temp.size()];
        temp.toArray(sessions);
        adapter = new SessionsAdapter(
                this,
                sessions);

        list.setOnItemClickListener(new OnSessionClick());
        newItemButton.setText(R.string.add_new_sesion);
        newItemButton.setOnClickListener(new NewSessionAction());
    }
/*
    @Override
    protected void changeLanguage(String lang) {

    }
*/
    private class OnSessionClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemId=sessions[position].getId();

            Intent intent= new Intent(getApplicationContext(),ModifySession.class);
            intent.putExtra("coursId",cours.getId());
            intent.putExtra("sessionId",itemId);
            startActivity(intent);
        }
    }
    private class  NewSessionAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifySession.class);
            intent.putExtra("coursId",cours.getId());
            intent.putExtra("sessionId",-1);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}
