package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
public class SessionData  {
    //TODO passer les STring en char[]
    int id;
    int coursId;
    int salleId;
    Date start;
    Date end;

    SessionData getCopie(){
        SessionData copie = new SessionData();
        copie.id=this.id;
        copie.coursId=this.coursId;
        copie.salleId=this.salleId;
        copie.start=this.start;
        copie.end=this.end;
        return copie;
    }

}
