package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Student implements Comparable<Student>{


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
        return new StudentModificator(PersonData.getDefault());
    }
    void update (PersonData data){
        if(this.data.id== data.id)
            this.data=data;
    }
    @Override
    public String toString(){
        return data.name +" "+data.firstname;
    }

    @Override
    public int compareTo(Student another) {
        return this.data.name.compareTo(another.data.name);
    }
}
