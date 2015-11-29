package com.cours644_1.maa_bom.ittrainingapp.DataObjects;

/**
 * Created by arnaud on 14.11.2015.
 */
public class Teacher implements Comparable<Teacher>{


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
        return new TeacherModificator(PersonData.getDefault());
    }
    void update (PersonData data){
        if(this.data.id== data.id)
            this.data=data;
    }
    public String toString(){
        return data.name +" "+data.firstname;
    }

    @Override
    public int compareTo(Teacher another) {
        return this.data.name.compareTo(another.data.name);
    }

    @Override
    public boolean equals(Object o){
        try {
            return this.data.id==((Teacher)o).data.id;
        }catch (Exception e) {}
        return false;

    }
}
