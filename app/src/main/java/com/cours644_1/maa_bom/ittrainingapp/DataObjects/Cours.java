package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Cours implements Comparable<Cours>{
    protected CoursData data;

    Cours(CoursData data){
        this.data= data;
    }

    public int getId(){
        return data.id;
    }
    public String getName(){
        return data.name;
    }
    public String getDescription(){
        return data.description;
    }
    public CoursModificator getModificator() {
        return new CoursModificator(data.getCopie());
    }

    void update (CoursData data){
        if(this.data.id== data.id)
            this.data=data;
    }
    @Override
    public String toString(){
        return data.name;
    }
    public static  CoursModificator newForCreation(){
        return new CoursModificator(CoursData.getDefault());
    }

    @Override
    public int compareTo(Cours another) {
        return this.data.name.compareTo(another.data.name);
    }
}
