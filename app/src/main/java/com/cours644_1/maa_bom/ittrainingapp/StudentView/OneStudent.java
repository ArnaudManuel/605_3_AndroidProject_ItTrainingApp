package com.cours644_1.maa_bom.ittrainingapp.StudentView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.MainActivity;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;

/**
 * Created by arnaud on 19.11.2015.
 */
public final class OneStudent extends SelectionList {
    @Override
    protected void specialise() {
        items = dataStore.getStudentsList().toArray();

        adapter = new ArrayAdapter<Object>(
                this,
                R.layout.element_list_person,
                items);

        list.setOnItemClickListener(new OnStudentClick());
        newItemButton.setText(R.string.add_student);
        newItemButton.setOnClickListener(new NewStudentAction());

    }

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


    private class NewStudentAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyStudent.class);
            intent.putExtra("personId",-1);
            startActivity(intent);
        }
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
