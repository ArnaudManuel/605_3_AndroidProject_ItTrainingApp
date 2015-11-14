package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Student {


    protected PersonData data;

    Student (PersonData data){
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
    public StudentModificator getModificator(){
        return new StudentModificator(data.getCopie());
    }
    public static StudentModificator newForCreation(){
        return new StudentModificator(new PersonData());
    }

}
