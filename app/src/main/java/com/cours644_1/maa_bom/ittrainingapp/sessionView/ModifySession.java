package com.cours644_1.maa_bom.ittrainingapp.sessionView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
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

    private Cours cours;
    private TextView nameTxt;
    private TextView descriptionTxt;
    private DataStore dataStore;

    private SessionModificator session;
    private TextView startDayTxt;
    private TextView startTimeTxt;
    private TextView endDayTxt;
    private TextView endTimeTxt;
    private TextView roomTxt;
    private Button saveButton;
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
        startDayTxt = (TextView)findViewById(R.id.act_session_modify_start_date);
        startTimeTxt = (TextView)findViewById(R.id.act_session_modify_start_time);
        endDayTxt = (TextView)findViewById(R.id.act_session_modify_end_date);
        endTimeTxt = (TextView)findViewById(R.id.act_session_modify_end_time);
        roomTxt = (TextView)findViewById(R.id.act_session_modify_roomNumberTxt);
        saveButton = (Button)findViewById(R.id.act_session_modify_save_Button);
        dayFormat = new SimpleDateFormat("yyyy/dd/MM");//// TODO: 21.11.2015 localiser préférence affichage de l'heure
        timeFormat= new SimpleDateFormat("HH:mm");//TODO localiser la préférence de l'afichage (24 heure | am/pm)
        start=Calendar.getInstance();
        end=Calendar.getInstance();
        dataStore = DataGeneralStore.getStore(getApplicationContext());

        //récupération du cours courrant
        cours= dataStore.getCoursById(getIntent().getExtras().getInt("coursId"));
        //récupération de la seesion courante ou d'une nouvelle session pour le cours
        int sessionId = getIntent().getExtras().getInt("sessionId", -1);
        if (sessionId < 0)
            session = cours.getNewSession();
        else
            session= dataStore.getSessionById(sessionId).getModificator();
        //encapsulation des dates dans des Calendar
        start.setTime(session.getStart());
        end.setTime(session.getEnd());

        //ecriture des valeures dans les champs
        nameTxt.setText(cours.getName());
        descriptionTxt.setText(cours.getDescription());
        startDayTxt.setOnClickListener(new OnDayClick(OnDayClick.START));
        startTimeTxt.setOnClickListener(new OnTimeClick(OnTimeClick.START));
        endDayTxt.setOnClickListener(new OnDayClick(OnDayClick.END));
        endTimeTxt.setOnClickListener(new OnTimeClick(OnTimeClick.END));
        refreshTimeField();
        saveButton.setOnClickListener(new SaveAction());


        if(session.getRoomName().equals("")) {
            roomTxt.setText("no room atached yet");
            updateRooms();
            setRandomRoom();
            roomTxt.setText(session.getRoomName());
        }
        else
            roomTxt.setText(session.getRoomName());

    }
    private void setRandomRoom(){
        session.setRoom(availableRooms[(int)(Math.random()*availableRooms.length)]);
    }
    private void refreshTimeField(){
        startDayTxt.setText(dayFormat.format(start.getTime()));
        startTimeTxt.setText(timeFormat.format(start.getTime()));
        endDayTxt.setText(dayFormat.format(end.getTime()));
        endTimeTxt.setText(timeFormat.format(end.getTime()));
    }



    private void updateRooms(){
        //// TODO: 24.11.2015 only availabel rooms
        List<Room> temp=dataStore.getAvailableRooms(start.getTime(), end.getTime());
        availableRooms= new Room[temp.size()];
        //// TODO: 24.11.2015   test vide?
        temp.toArray(availableRooms);
    }

    private class OnDayClick implements View.OnClickListener{
        private final static int START =0;
        private final static int END=1;
        private int mode;
        private DatePickerDialog dialog;


        private OnDayClick(int MODE){
            mode=MODE%2;
        }

        @Override
        public void onClick(View v) {
            switch (mode){
                case 0:
                    dialog= new DatePickerDialog(
                            getApplicationContext()
                            , new DateSeter()
                            ,start.get(Calendar.YEAR)
                            ,start.get(Calendar.MONTH)
                            ,start.get(Calendar.DAY_OF_MONTH));
                    break;
                case 1:
                    dialog= new DatePickerDialog(
                            getApplicationContext()
                            , new DateSeter()
                            ,end.get(Calendar.YEAR)
                            ,end.get(Calendar.MONTH)
                            ,end.get(Calendar.DAY_OF_MONTH));
                    break;
            }
            dialog.show();
        }
        private class DateSeter implements DatePickerDialog.OnDateSetListener{


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                switch (mode){
                    case 0:
                        start.set(year, monthOfYear, dayOfMonth);
                        if (start.after(end))
                            end.setTime(start.getTime());
                        break;
                    case 1:
                        end.set(year,monthOfYear,dayOfMonth);
                        if (end.before(start))
                            start.setTime(end.getTime());
                        break;
                }
                refreshTimeField();
                //// TODO: 24.11.2015 controle disponivbilité salles
            }
        }
    }
    private class OnTimeClick implements View.OnClickListener{
        private final static int START =0;
        private final static int END=1;
        private int mode;
        private TimePickerDialog dialog;

        private OnTimeClick(int MODE){
            mode=MODE%2;
        }

        @Override
        public void onClick(View v) {
            switch (mode){
                case 0:
                    dialog= new TimePickerDialog(
                            getApplicationContext()
                            ,new TimeSeter()
                            ,start.get(Calendar.HOUR_OF_DAY)
                            ,start.get(Calendar.MINUTE)
                            ,true //TODO représente l'heure sur 24 heure (true) out sur 12 heure (false) place en fonction des préférences
                    );
                    break;
                case 1:
                    dialog= new TimePickerDialog(
                            getApplicationContext()
                            ,new TimeSeter()
                            ,end.get(Calendar.HOUR_OF_DAY)
                            ,end.get(Calendar.MINUTE)
                            ,true //TODO représente l'heure sur 24 heure (true) out sur 12 heure (false) place en fonction des préférences
                    );
                    break;
            }
            dialog.show();
        }
        private class TimeSeter implements TimePickerDialog.OnTimeSetListener{

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                switch (mode){
                    case 0:
                        start.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        start.set(Calendar.MINUTE,minute);
                        if (start.after(end))
                            end.setTime(start.getTime());
                        break;
                    case 1:
                        end.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        end.set(Calendar.MINUTE, minute);
                        if (end.before(start))
                            start.setTime(end.getTime());
                        break;
                }
                refreshTimeField();
                //// TODO: 24.11.2015 controle disponivbilité salles
            }
        }
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
            session.setStart(start.getTime());
            session.setEnd(end.getTime());
            //todo ajouté salle choisie
            dataStore.save(session);
            Intent intent = new Intent(getApplicationContext(), ShowCours.class);
            intent.putExtra("coursId",cours.getId());
            startActivity(intent);
        }
    }

}