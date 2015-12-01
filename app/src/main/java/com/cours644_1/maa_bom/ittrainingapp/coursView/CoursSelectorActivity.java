package com.cours644_1.maa_bom.ittrainingapp.coursView;

import android.view.View;

import com.cours644_1.maa_bom.ittrainingapp.BinaryArrayAdapter;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.SelectableItem;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by arnaud on 29.11.2015.
 */
public class CoursSelectorActivity extends SelectionList {


    private Student student;

    @Override
    protected void specialise() {
        student = dataStore.getStudentById(getIntent().getExtras().getInt("personId"));

        List<Cours> allCoursList = dataStore.getCoursList();
        Collections.sort(allCoursList);

        Cours[] currentCours = new Cours[0];
        {
            List<Cours> currentCourslist = dataStore.getCoursFor(student);
            Collections.sort(currentCourslist);
            currentCours = new Cours[currentCourslist.size()];
            currentCourslist.toArray(currentCours);
        }
        items = new SelectableItem[allCoursList.size()];
        Cours[] allCoursArry = new Cours[allCoursList.size()];
        allCoursList.toArray(allCoursArry);
        int currentPointer = 0;
        for (int cpt = 0; cpt < items.length; ++cpt) {
            items[cpt] = new SelectableItem(allCoursArry[cpt]);
            if (currentPointer < currentCours.length)
                if (allCoursArry[cpt].equals(currentCours[currentPointer])) {
                    ((SelectableItem) items[cpt]).setSelected(true);
                    ++currentPointer;
                }
        }

        adapter = new BinaryArrayAdapter(
                this,
                (SelectableItem[]) items,
                getString(R.string.in_charge),
                getString(R.string.not_in_charge)
        );

        newItemButton.setText(getString(R.string.act_save));
        newItemButton.setOnClickListener(new saveAction());
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private class saveAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            List<Cours> selectedCours = new ArrayList<Cours>();
            List<SelectableItem> selected = ((BinaryArrayAdapter) adapter).getSelected();
            for (SelectableItem item : selected)
                selectedCours.add((Cours) item.getContent());
            dataStore.setCours(student, selectedCours);
            CoursSelectorActivity.this.finish();
        }
    }


}
