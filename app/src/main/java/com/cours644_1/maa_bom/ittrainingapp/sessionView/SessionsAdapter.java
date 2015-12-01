package com.cours644_1.maa_bom.ittrainingapp.sessionView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cours644_1.maa_bom.ittrainingapp.DataObjects.Session;
import com.cours644_1.maa_bom.ittrainingapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by arnaud on 21.11.2015.
 */
public class SessionsAdapter extends ArrayAdapter<Session> {
    private Calendar startCal = Calendar.getInstance();
    private Calendar endCal = Calendar.getInstance();
    private static SimpleDateFormat fullFormat = new SimpleDateFormat("dd/MM':'  HH:mm");
    private static SimpleDateFormat smallFormat= new SimpleDateFormat("HH:mm");



    public SessionsAdapter(Context context, Session[] sessions) {
        super(context, 0, sessions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Session session = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session_in_list, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.session_in_list_cours_name)).setText(session.getCoursName());
        ((TextView) convertView.findViewById(R.id.session_in_list_room_name)).setText(session.getRoomName());
        ((TextView) convertView.findViewById(R.id.session_in_list_time_start)).setText(fullFormat.format(session.getStart()));
        {
            startCal.setTime(session.getStart());
            endCal.setTime(session.getEnd());
            //check if end and start are the same day, if not, show full date in time_end field
            if(startCal.get(Calendar.YEAR)==endCal.get(Calendar.YEAR)&&startCal.get(Calendar.DAY_OF_YEAR)==endCal.get(Calendar.DAY_OF_YEAR))
                ((TextView) convertView.findViewById(R.id.session_in_list_time_end)).setText(smallFormat.format(session.getEnd()));
            else
                ((TextView) convertView.findViewById(R.id.session_in_list_time_end)).setText(fullFormat.format(session.getEnd()));
        }
        ((TextView) convertView.findViewById(R.id.session_in_list_cours_desc)).setText(session.getCoursDesc());



        return convertView;
    }

}