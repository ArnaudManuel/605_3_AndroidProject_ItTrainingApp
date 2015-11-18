package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
class SessionData  {
    //TODO passer les STring en char[]
    int id;
    int coursId;
    int roomId;
    Date start;
    Date end;

    SessionData(int id, Cours cours, Room room, Date start, Date end){
        this(id, cours.getId(), room.getId(), start, end);
    }
    SessionData(int id,int coursId,  int roomId, Date start, Date end){
        this.id=id;
        this.coursId=coursId;
        this.roomId=roomId;
        this.start=start;
        this.end=end;
    }

    SessionData getCopie(){
        return new SessionData(id, coursId, roomId,  start, end);
    }

}
