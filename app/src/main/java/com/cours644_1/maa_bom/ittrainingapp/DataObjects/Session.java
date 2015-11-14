package com.cours644_1.maa_bom.ittrainingapp.DataObjects;
import java.util.Date;
/**
 * Created by arnaud on 14.11.2015.
 */
public class Session {
    protected SessionData data;

    Session(SessionData data){
        this.data=data;
    }

    public int getId(){
        return data.id;
    }
    public int getCoursId() {
        return data.coursId;
    }
    public int getSalleId() {
        return data.salleId;
    }
    public Date getStart() {
        return data.start;
    }
    public Date getEnd(){
        return data.end;
    }
    public SessionModificator getModificator(){
        return new SessionModificator(data.getCopie());
    }

}
