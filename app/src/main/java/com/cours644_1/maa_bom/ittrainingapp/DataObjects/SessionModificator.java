package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
public class SessionModificator extends Session {
    SessionModificator(SessionData data, Cours cours, Room room) {
        super(data, cours, room);
    }
    //SessionModificator(SessionData data){super( data);}

    public void setCours(Cours cours) {
        data.coursId=cours.getId();
        this.cours=cours;
    }
    public void setRoom(Room room) {
        if(room==null)
            data.roomId=-1;
        else {
            data.roomId = room.getId();
            this.room = room;
        }
    }

    public void setStart(Date start) {
        data.start=start ;
    }
    public void setEnd(Date end){
        data.end= end;
    }
}
