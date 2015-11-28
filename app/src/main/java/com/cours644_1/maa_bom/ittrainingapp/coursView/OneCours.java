package com.cours644_1.maa_bom.ittrainingapp.coursView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ModifyTeacher;

/**
 * Created by arnaud on 20.11.2015.
 */
public class OneCours extends SelectionList {
    @Override
    protected void specialise() {
        items = dataStore.getCoursList().toArray();
        adapter = new ArrayAdapter<Object>(
                this,
                R.layout.element_list_person,
                items);

        list.setOnItemClickListener(new OnCoursClick());
        newItemButton.setText("add Cours");// // TODO: 21.11.2015 localiser ressource
        newItemButton.setOnClickListener(new NewCorsAction());
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
