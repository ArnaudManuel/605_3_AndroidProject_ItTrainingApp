package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;

import java.util.Locale;

/**
 * Created by arnaud on 19.11.2015.
 */
public final class OneTeacher extends SelectionList {
    @Override
    protected void specialise() {
        items = dataStore.getTeachersList().toArray();


      adapter = new ArrayAdapter<Object>(
                this,
                R.layout.element_list_person,
                items);

        list.setOnItemClickListener(new OnTeacherClick());
        newItemButton.setText(R.string.add_teacher);
        newItemButton.setOnClickListener(new NewTeacherAction());
    }
/*
    @Override
    protected void changeLanguage(String lang) {
    }
*/
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

    private class OnTeacherClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemId=((Teacher)items[position]).getId();

            Intent intent= new Intent(getApplicationContext(),ShowTeacher.class);
            intent.putExtra("personId",itemId);
            startActivity(intent);
        }
    }

    private class NewTeacherAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyTeacher.class);
            intent.putExtra("personId",-1);
            startActivity(intent);
        }
    }



}
