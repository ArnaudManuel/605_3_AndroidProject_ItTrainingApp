package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Teacher {


    protected PersonData data;

    Teacher (PersonData data){
        this.data=data;
    }


    public int getId(){
        return data.id;
    }
    public String getName(){
        return data.name;
    }
    public String getFirstname(){
        return data.firstname;
    }
    public String getMail(){
        return data.mail;
    }
    public String getDescription(){
        return data.description;
    }
    public TeacherModificator getModificator(){
        return new TeacherModificator(data.getCopie());
    }
    public static TeacherModificator newForCreation(){
        return new TeacherModificator(new PersonData());
    }
}
