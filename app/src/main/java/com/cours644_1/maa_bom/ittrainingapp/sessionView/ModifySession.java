package com.cours644_1.maa_bom.ittrainingapp.sessionView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentContainer;
import android.app.FragmentController;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Cours;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataGeneralStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.DataStore;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Room;
import com.cours644_1.maa_bom.ittrainingapp.DataObjects.SessionModificator;
import com.cours644_1.maa_bom.ittrainingapp.R;
import com.cours644_1.maa_bom.ittrainingapp.coursView.ShowCours;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by arnaud on 23.11.2015.
 */
public class ModifySession extends Activity {
    private static String NO_ROOM_AVAILABLE_ALERT="no room available for the selected period";//// TODO: 30.11.2015 localiser ressource
    private static String NO_ROOM_SELECTED_ALERT="no room selected";
    private Cours cours;
    private TextView nameTxt;
    private TextView descriptionTxt;
    private DataStore dataStore;

    private SessionModificator session;
    private EditText startDayTxt;
    private EditText startTimeTxt;
    private EditText endDayTxt;
    private EditText endTimeTxt;
    private TextView roomTxt;
    private Button saveButton;
    private Button deleteButton;
    private SimpleDateFormat dayFormat;
    private SimpleDateFormat timeFormat;
    private Calendar start;
    private Calendar end;

    private Room[]availableRooms;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_session_modify);

        //Instanciation
        nameTxt=(TextView)findViewById(R.id.act_session_modify_cours_nameTxt);
        descriptionTxt = (TextView)findViewById(R.id.act_session_modify_cours_descriptionTxt);
        startDayTxt = (EditText)findViewById(R.id.act_session_modify_start_date);
        startTimeTxt = (EditText)findViewById(R.id.act_session_modify_start_time);
        endDayTxt = (EditText)findViewById(R.id.act_session_modify_end_date);
        endTimeTxt = (EditText)findViewById(R.id.act_session_modify_end_time);
        roomTxt = (TextView)findViewById(R.id.act_session_modify_roomNumberTxt);
        saveButton = (Button)findViewById(R.id.act_session_modify_save_Button);
        dayFormat = new SimpleDateFormat("yyyy/dd/MM");//// TODO: 21.11.2015 localiser préférence affichage de l'heure
        timeFormat= new SimpleDateFormat("HH:mm");//TODO localiser la préférence de l'afichage (24 heure | am/pm)
        start=Calendar.getInstance();
        end=Calendar.getInstance();
        dataStore = DataGeneralStore.getStore(getApplicationContext());
        deleteButton = (Button) findViewById(R.id.act_session_modify_deleteButton);

        //récupération du cours courrant
        cours= dataStore.getCoursById(getIntent().getExtras().getInt("coursId"));
        //récupération de la seesion courante ou d'une nouvelle session pour le cours
        int sessionId = getIntent().getExtras().getInt("sessionId", -1);
        if (sessionId < 0) {
            session = cours.getNewSession();
            deleteButton.setVisibility(View.INVISIBLE);
        }
        else {
            session = dataStore.getSessionById(sessionId).getModificator();
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new DeleteSessionAction());
        }


        //encapsulation des dates dans des Calendar
        start.setTime(session.getStart());
        end.setTime(session.getEnd());

        //ecriture des valeures dans les champs
        nameTxt.setText(cours.getName());
        descriptionTxt.setText(cours.getDescription());
        saveButton.setOnClickListener(new SaveAction());

        refreshTimeField();

        //function call datepicker
        startDayTxt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                DialogFragment newFragment = new DateDialog(DateDialog.START);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        endDayTxt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                DialogFragment newFragment = new DateDialog(DateDialog.END);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        //function call timepicker
        startTimeTxt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                DialogFragment newFragment = new TimeDialog(TimeDialog.START);
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        endTimeTxt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            public void onClick(View v) {
                DialogFragment newFragment = new TimeDialog(TimeDialog.END);
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        //updateRooms(); unecessary cause: called in refreshTimeField methode call before

    }

    private void setRoom(int index){
        if (index<availableRooms.length){
            session.setRoom(availableRooms[index]);
            roomTxt.setText(availableRooms[index].getName());
        }
        else
        {
            session.setRoom(null);
            roomTxt.setText(NO_ROOM_SELECTED_ALERT);
        }
    }
    private boolean checkRoomavailability(Room room){
        if(room==null)
            return false;
        for (int cpt=0;cpt<availableRooms.length;++cpt)
            if (room.equals(availableRooms[cpt]))
                return true;
        return false;
    }

    private void refreshTimeField(){
        startDayTxt.setText(dayFormat.format(start.getTime()));
        startTimeTxt.setText(timeFormat.format(start.getTime()));
        endDayTxt.setText(dayFormat.format(end.getTime()));
        endTimeTxt.setText(timeFormat.format(end.getTime()));
        updateRooms();
    }



    private void updateRooms(){
        List<Room> temp=dataStore.getAvailableRooms(start.getTime(), end.getTime(),session);
        availableRooms= new Room[temp.size()];
        temp.toArray(availableRooms);
        if (availableRooms.length==0) {
            Toast.makeText(
                    getApplicationContext(),
                    NO_ROOM_AVAILABLE_ALERT,
                    Toast.LENGTH_LONG
            ).show();
            setRoom(0);
        }
        else
            if(false==checkRoomavailability(session.getRoom())){
                setRoom(0);
                Toast.makeText(
                        getApplicationContext(),
                        "new room atribued",
                        Toast.LENGTH_LONG
                ).show();
            }
            else
                roomTxt.setText(session.getRoomName());

    }



    private class OnRoomClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
        private class RoomSeter extends Dialog{


            public RoomSeter(Context context) {
                super(context);
            }
        }
    }
    private class SaveAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            updateRooms();
            if(session.getRoom()==null){
                Toast.makeText(
                        getApplicationContext(),
                        "Unable to save: "+NO_ROOM_AVAILABLE_ALERT,//// TODO: 30.11.2015 loclaiser
                        Toast.LENGTH_LONG)
                        .show();
            }
            else {
                session.setStart(start.getTime());
                session.setEnd(end.getTime());
                dataStore.save(session);
                ModifySession.this.finish();
            }

        }
    }



    @SuppressLint({"NewApi", "ValidFragment"})
    public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private final static int START =0;
        private final static int END=1;
        private int mode;

        private DateDialog(int MODE){
            mode=MODE%2;
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int year = -1;
            int month= -1;
            int day = -1;

            //change date end/start
            switch (mode){
                case 0:
                    day = start.get(Calendar.DAY_OF_MONTH);
                    month = start.get(Calendar.MONTH);
                    year = start.get(Calendar.YEAR);
                    break;
                case 1:
                    day = end.get(Calendar.DAY_OF_MONTH);
                    month = end.get(Calendar.MONTH);
                    year = end.get(Calendar.YEAR);
                    break;
            }
            DatePickerDialog dpd = new DatePickerDialog(ModifySession.this, this, year, month, day);
            return dpd;
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
            switch (mode){
                case 0:
                    start.set(year, month, day);
                    if (start.after(end))
                        end.setTime(start.getTime());
                    break;
                case 1:
                    end.set(year,month,day);
                    if (end.before(start))
                        start.setTime(end.getTime());
                    break;
            }
            refreshTimeField();
        }
    }



    //function for timepicker

    @SuppressLint({"NewApi", "ValidFragment"})
    public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        private final static int START =0;
        private final static int END=1;
        private int mode;

        private TimeDialog(int MODE){
            mode=MODE%2;
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int hour = -1;
            int min= -1;

            //change time end/start
            switch (mode){
                case 0:
                    hour = start.get(Calendar.HOUR_OF_DAY);
                    min = start.get(Calendar.MINUTE);
                    break;
                case 1:
                    hour = end.get(Calendar.HOUR_OF_DAY);
                    min = end.get(Calendar.MINUTE);
                    break;
            }
            TimePickerDialog dpd = new TimePickerDialog(ModifySession.this, this, hour, min, true);
            return dpd;
        }

        @Override
        public void onTimeSet(TimePicker view, int hour, int min) {
            switch (mode){
                case 0:
                    start.set(Calendar.HOUR_OF_DAY, hour);
                    start.set(Calendar.MINUTE, min);
                    if (start.after(end))
                        end.setTime(start.getTime());
                    break;
                case 1:
                    end.set(Calendar.HOUR_OF_DAY, hour);
                    end.set(Calendar.MINUTE, min);
                    if (end.before(start))
                        start.setTime(end.getTime());
                    break;
            }
            refreshTimeField();
        }
    }

    private class DeleteSessionAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            dataStore.delete(session);
            ModifySession.this.finish();
        }
    }


}