package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
public class Session implements Comparable<Session>{
    protected SessionData data;
    protected Cours cours;
    protected Room room;


    //// TODO: 23.11.2015 changer le constructeur, pouvoir créer avec le seul session data et rechercher au besion les cours et salles

    Session(SessionData data, Cours cours, Room room){
        this.data=data;
        this.cours=cours;
        this.room=room;
    }
    //Session(SessionData data){this.data=data;}


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
    public Room getRoom(){return room;}

    public String getCoursName(){
        return cours.getName();
    }

    public String getRoomName(){
        if (data.roomId<0)
            return "";
        return room.getName();
    }
    public String getCoursDesc(){return cours.getDescription();}

    public SessionModificator getModificator(){
        return new SessionModificator(data.getCopie(), cours, room);
    }
    static SessionModificator getDefault(Cours cours){
        return new SessionModificator(new SessionData(-1,cours.getId(),-1,new Date(),new Date()),cours, null);
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
