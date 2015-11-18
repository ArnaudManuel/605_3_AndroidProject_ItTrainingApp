package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
class PersonData {
    //TODO passer les STring en char[]

    int id;
    String name;
    String firstname;
    String mail;
    String description;
    boolean isStudent;
    boolean isTeacher;



    PersonData(int id, String name, String firstname, String mail, String description, boolean isStudent, boolean isTeacher){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.mail=mail;
        this.description= description;
        this.isStudent=isStudent;
        this.isTeacher=isTeacher;
    }

    PersonData getCopie(){
        return new PersonData(id, name, firstname, mail, description, isStudent, isTeacher);
    }
    static PersonData getDefault(){
        return new PersonData(-1, "", "", "", "", false, false);
    }
}
