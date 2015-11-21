package com.cours644_1.maa_bom.ittrainingapp.coursView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;

/**
 * Created by arnaud on 20.11.2015.
 */
public class ShowCours extends Activity {
    private Cours cours;
    private TextView nameTxt;
    private TextView descriptionTxt;
    private Button modifyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cours_show);


        cours= DataGeneralStore.store.getCoursById(getIntent().getExtras().getInt("coursId"));

        nameTxt=(TextView)findViewById(R.id.act_cours_show_nameTxt);
        descriptionTxt = (TextView)findViewById(R.id.act_cours_show_descriptionTxt);
        modifyButton = (Button) findViewById(R.id.act_cours_show_descriptionTxt);


        nameTxt.setText(cours.getName());
        descriptionTxt.setText(cours.getDescription());
        modifyButton.setOnClickListener(new ModifyCoursAction());
    }

    private class ModifyCoursAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyStudent.class);
            intent.putExtra("coursId",cours.getId());
            startActivity(intent);
        }
    }
}