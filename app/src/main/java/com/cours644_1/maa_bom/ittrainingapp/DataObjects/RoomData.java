package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
class RoomData {
    //TODO passer les STring en char[]

    int id;
    char[] name;

    RoomData(int id, String name){
        this.id=id;
        this.name= new char[]{name.charAt(0),name.charAt(1),name.charAt(2)};
    }


}
