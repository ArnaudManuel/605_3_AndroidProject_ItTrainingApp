package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class PersonData {
    //TODO passer les STring en char[]

    int id=-1;
    String name="";
    String firstname="";
    String mail="";
    String description="";


    PersonData(){
        id=-1;
        name="";
        firstname="";
        mail="";
        description="";
    }
    PersonData(int id, String name, String firstname, String mail, String description){
        this.id=id;
        this.name=name;
        this.firstname=firstname;
        this.mail=mail;
        this.description= description;
    }

    PersonData getCopie(){
        PersonData copie = new PersonData();
        copie.id=this.id;
        copie.name= this.name;
        copie.firstname=this.firstname;
        copie.mail=this.mail;
        copie.description=this.description;
        return copie;
    }
}
