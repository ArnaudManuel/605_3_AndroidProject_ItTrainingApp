package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Room {

    protected RoomData data;

    Room (RoomData data){
        this.data=data;
    }
    public int getId(){
        return data.id;
    }
    public String getName(){
        String name="";
        for (int cpt = 0; cpt < data.name.length; cpt++)
            name=name + data.name[cpt];
        return name;
    }

}