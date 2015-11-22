package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.MainActivity;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;

/**
 * Created by arnaud on 19.11.2015.
 */
public final class OneStudent extends SelectionList {
    @Override
    protected void populateListView() {
        items = DataGeneralStore.store.getStudentsList().toArray();

        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                this,
                R.layout.element_list_person,
                items);

        list.setAdapter(adapter);
    }




    @Override
    protected void setRegisterItemsClickCallback() {
        list.setOnItemClickListener(new OnStudentClick());
    }

    @Override
    protected void setButtonAction() {
        newItemButton.setText("add Student");//// TODO: 19.11.2015 localiser ressource
        newItemButton.setOnClickListener(new NewStudentAction());
    }
   private class OnStudentClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemId=((Student)items[position]).getId();

            Intent intent= new Intent(getApplicationContext(),ShowStudent.class);
            intent.putExtra("personId",itemId);
            startActivity(intent);
        }
    }



}
