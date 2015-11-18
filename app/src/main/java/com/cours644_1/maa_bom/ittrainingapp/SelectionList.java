package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;


public class SelectionList extends Activity {
    Object[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_list);
        populateListView();
        registerStudentClickCallback();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection_list, menu);
        return true;
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

                try {
                    int itemId=((Student)items[position]).getId();

                    Intent intent= new Intent(getApplicationContext(),ShowStudent.class);
                    intent.putExtra("personId",itemId);
                    startActivity(intent);

                }catch (ClassCastException e){}
            }
        });
    }


}
