package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;

/**
 * Created by arnaud on 19.11.2015.
 */
public class OneTeacher extends SelectionList {
    @Override
    protected void populateListView() {
        items = DataGeneralStore.store.getTeachersList().toArray();

        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                this,
                R.layout.element_list_person,
                items);
        list.setAdapter(adapter);
    }

    @Override
    protected void setRegisterItemsClickCallback() {
        list.setOnItemClickListener(new OnTeacherClick());
    }

    @Override
    protected void setButtonAction() {
        newItemButton.setText("add teacher");//// TODO: 19.11.2015 localiser ressource
        newItemButton.setOnClickListener(new NewTeacherAction());
    }




}
