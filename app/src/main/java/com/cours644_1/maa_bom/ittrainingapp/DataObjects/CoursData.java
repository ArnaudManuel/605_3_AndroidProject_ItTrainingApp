package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class CoursData {
    //TODO passer les STring en char[]

    int id;
    String name;
    String description;

    CoursData getCopie(){
        CoursData copie = new CoursData();
        copie.id=this.id;
        copie.name= this.name;
        copie.description=this.description;
        return copie;
    }
}
