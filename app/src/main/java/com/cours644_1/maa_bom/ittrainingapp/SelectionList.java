package com.cours644_1.maa_bom.ittrainingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Student;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Teacher;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ModifyStudent;
import com.cours644_1.maa_bom.ittrainingapp.StudentView.ShowStudent;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ModifyTeacher;
import com.cours644_1.maa_bom.ittrainingapp.teacherView.ShowTeacher;


public abstract class SelectionList extends Activity {
    protected Object[] items;
    protected Button newItemButton;
    protected ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_list);


        list = (ListView) findViewById(R.id.selection_view);
        newItemButton = (Button) findViewById(R.id.slection_list__add_new_item_button);

        populateListView();
        setRegisterItemsClickCallback();
        setButtonAction();
    }

    protected abstract void populateListView();
    protected abstract void setRegisterItemsClickCallback();
    protected abstract void setButtonAction();


    public class OnTeacherClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemId=((Teacher)items[position]).getId();

            Intent intent= new Intent(getApplicationContext(),ShowTeacher.class);
            intent.putExtra("personId",itemId);
            startActivity(intent);
        }
    }

    public class OnStudentClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int itemId=((Student)items[position]).getId();

            Intent intent= new Intent(getApplicationContext(),ShowStudent.class);
            intent.putExtra("personId",itemId);
            startActivity(intent);
        }
    }

    public class NewTeacherAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyTeacher.class);
            intent.putExtra("personId",-1);
            startActivity(intent);
        }
    }

    public class NewStudentAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getApplicationContext(),ModifyStudent.class);
            intent.putExtra("personId",-1);
            startActivity(intent);
        }
    }

}
