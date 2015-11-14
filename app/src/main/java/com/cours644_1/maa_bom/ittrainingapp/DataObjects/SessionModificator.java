package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
public class SessionModificator extends Session {

    SessionModificator(SessionData data) {
        super(data);
    }

    public void getcoursId(Cours cours) {
        data.coursId=cours.getId();
    }
    public void setsalleId() {
        //TODO mettre salle;
    }
    public void setStart(Date start) {
        data.start=start ;
    }
    public void getEnd(Date end){
        data.end= end;
    }
    public boolean save(){
        //TODO sauvegarde
        return false;
    }

}
