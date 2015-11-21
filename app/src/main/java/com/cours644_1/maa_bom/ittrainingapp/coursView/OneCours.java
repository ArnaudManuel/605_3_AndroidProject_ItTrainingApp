package com.cours644_1.maa_bom.ittrainingapp.coursView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ModifyTeacher;

/**
 * Created by arnaud on 20.11.2015.
 */
public class OneCours extends SelectionList {
    @Override
    protected void populateListView() {
        items = DataGeneralStore.store.getCoursList().toArray();

        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                this,
                R.layout.element_list_person,
                items);

        list.setAdapter(adapter);
    }

    @Override
    protected void setRegisterItemsClickCallback() {
        list.setOnItemClickListener(new OnCoursClick());
    }

    @Override
    protected void setButtonAction() {
        newItemButton.setText("add Cours");// // TODO: 21.11.2015 localiser ressource
        newItemButton.setOnClickListener(new NewCorsAction());
    }



    private class  OnCoursClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemId=((Cours)items[position]).getId();

            Intent intent= new Intent(getApplicationContext(),ShowCours.class);
            intent.putExtra("coursId",itemId);// TODO: 21.11.2015 mettre danas un ficheir de ressource
            startActivity(intent);
        }
    }

    private class NewCorsAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyCours.class);
            intent.putExtra("coursId",-1);//// TODO: 21.11.2015 mettre danas un ficheir de ressource
            startActivity(intent);
        }
    }
}
