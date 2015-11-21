package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class TeacherModificator extends Teacher {

    TeacherModificator(PersonData data) {
        super(data);
        data.isTeacher=true;
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
    public void setDescription(String description){
        data.description=description;
    }


    public void save(){
        data.isTeacher=true;
        DataGeneralStore.store.save(this);
    }

}
