package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;


public class SelectionList extends Activity {
    Object[] items;
    Button newStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_list);

        newStudentButton = (Button) findViewById(R.id.slection_list__add_new_item_button);
        newStudentButton.setOnClickListener(new addNewStudent());



        populateListView();
        registerStudentClickCallback();
    }





    private void populateListView() {

        items = DataGeneralStore.store.getStudentsList().toArray();

        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                this,
                R.layout.lelement_student,
                items);
        ListView list = (ListView) findViewById(R.id.selection_view);
        list.setAdapter(adapter);
    }
    private void registerStudentClickCallback(){
        ListView list = (ListView)findViewById(R.id.selection_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    int itemId=((Student)items[position]).getId();

                    Intent intent= new Intent(getApplicationContext(),ShowStudent.class);
                    intent.putExtra("personId",itemId);
                    startActivity(intent);


            }
        });
    }
    private class addNewStudent implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyStudent.class);
            intent.putExtra("personId",-1);
            startActivity(intent);
        }
    }


}
