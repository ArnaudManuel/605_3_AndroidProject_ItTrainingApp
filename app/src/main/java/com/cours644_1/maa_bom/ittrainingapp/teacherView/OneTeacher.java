package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;
import com.cours644_1.maa_bom.ittrainingapp.SettingsActivity;

/**
 * Created by arnaud on 19.11.2015.
 */
public final class OneTeacher extends SelectionList {
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
