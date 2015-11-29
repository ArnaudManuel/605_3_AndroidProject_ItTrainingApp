package com.cours644_1.maa_bom.ittrainingapp.teacherView;

import android.view.View;

import com.cours644_1.maa_bom.ittrainingapp.BinaryArrayAdapter;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.SelectableItem;
import com.cours644_1.maa_bom.ittrainingapp.SelectionList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by arnaud on 29.11.2015.
 */
public class MultipleTeacher extends SelectionList {


    private Cours cours;

    @Override
    protected void specialise() {
        cours = dataStore.getCoursById(getIntent().getExtras().getInt("coursId"));

        Teacher[] allTeacherArry;
        {
            List<Teacher> allTeacherList = dataStore.getTeachersList();
            Collections.sort(allTeacherList);
            allTeacherArry = new Teacher[allTeacherList.size()];
            allTeacherList.toArray(allTeacherArry);
        }
        Teacher[] currentTeachers;
        {
            List<Teacher> currentTeacherlist = dataStore.getTeachersList(cours);
            Collections.sort(currentTeacherlist);
            currentTeachers = new Teacher[currentTeacherlist.size()];
            currentTeacherlist.toArray(currentTeachers);
        }
        items = new SelectableItem[allTeacherArry.length];
        int currentPointer = 0;
        for (int cpt = 0; cpt < items.length; ++cpt) {
            items[cpt] = new SelectableItem(allTeacherArry[cpt]);
            if (currentPointer < currentTeachers.length)
                if (allTeacherArry[cpt].equals(currentTeachers[currentPointer])) {
                    ((SelectableItem) items[cpt]).setSelected(true);
                    ++currentPointer;
                }
        }

        adapter = new BinaryArrayAdapter(
                this,
                (SelectableItem[]) items,
                "In charge",//TODO localisation
                "Unconcerned"//TODO localisation
        );

        newItemButton.setText("save modifications");// // TODO: 21.11.2015 localiser ressource
        newItemButton.setOnClickListener(new saveAction());
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private class saveAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            List<Teacher> selectedTeacher = new ArrayList<Teacher>();
            List<SelectableItem> selected = ((BinaryArrayAdapter) adapter).getSelected();
            for (SelectableItem item : selected)
                selectedTeacher.add((Teacher) item.getContent());
            dataStore.setTeacher(cours, selectedTeacher);
            MultipleTeacher.this.finish();

        }
    }


}
