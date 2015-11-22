package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
public class Session implements Comparable<Session>{
    protected SessionData data;
    private Cours cours;
    private Room room;

    Session(SessionData data, Cours cours, Room room){
        this.data=data;
        this.cours=cours;
        this.room=room;
    }

    public int getId(){
        return data.id;
    }
    public int getCoursId() {
        return data.coursId;
    }
    public int getSalleId() {
        return data.roomId;
    }
    public Date getStart() {
        return data.start;
    }
    public Date getEnd(){
        return data.end;
    }

    public String getCoursName(){return cours.getName();}
    public String getRoomName(){return room.getName();}
    public String getCoursDesc(){return cours.getDescription();}

    public SessionModificator getModificator(){
        return new SessionModificator(data.getCopie(), cours, room);
    }

    void update (SessionData data){
        if(this.data.id== data.id)
            this.data=data;
    }

    @Override
    public int compareTo(Session another) {
         int result;
        if((result=this.data.start.compareTo(another.data.start))==0)
            if((result=this.cours.compareTo(another.cours))==0);
        return  result;

    }
}
