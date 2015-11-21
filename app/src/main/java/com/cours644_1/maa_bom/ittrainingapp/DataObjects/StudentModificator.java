package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class StudentModificator extends Student {

    StudentModificator(PersonData data) {
        super(data);
        data.isStudent=true;
    }
    public void setName(String name){
        data.name= name;
    }
    public void setFirstname(String firstname){
        data.firstname=firstname;
    }
    public void setMail(String mail){
        data.mail=mail;
    }


    public void save(){
        data.isStudent=true;
        DataGeneralStore.store.save(this);
    }

}
