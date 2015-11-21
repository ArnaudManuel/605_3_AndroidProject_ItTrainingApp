package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
class CoursData {
    //TODO passer les STring en char[]

    int id=-1;
    String name="";
    String description="";

    CoursData(){}

    CoursData(int id, String name, String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }
    static CoursData getDefault(){
        return new CoursData(-1, "","");
    }

    CoursData getCopie(){
        return new CoursData(id,name,description);
    }
}
