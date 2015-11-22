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

/**
 * Created by arnaud on 21.11.2015.
 */
public class SessionsAdapter extends ArrayAdapter<Session> {



    public SessionsAdapter(Context context, Session[] sessions) {
        super(context, 0, sessions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Session session = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session_in_list, parent, false);
        }

        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM':'  HH:mm");//// TODO: 21.11.2015 localiser préférence affichage de l'heure
        SimpleDateFormat endFormat= new SimpleDateFormat("HH:mm");
        ((TextView) convertView.findViewById(R.id.session_in_list_cours_name)).setText(session.getCoursName());
        ((TextView) convertView.findViewById(R.id.session_in_list_room_name)).setText(session.getRoomName());
        ((TextView) convertView.findViewById(R.id.session_in_list_time_start)).setText(startFormat.format(session.getStart()));
        ((TextView) convertView.findViewById(R.id.session_in_list_time_end)).setText(endFormat.format(session.getEnd()));
        ((TextView) convertView.findViewById(R.id.session_in_list_cours_desc)).setText(session.getCoursDesc());



        return convertView;
    }

}