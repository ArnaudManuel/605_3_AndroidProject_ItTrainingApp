package com.example.Maxouille.myapplication.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Maximilien on 09.01.2016.
 */

@Entity
public class PersonDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String firstname;
    String mail;
    String description;
    boolean isStudent;
    boolean isTeacher;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMail() {
        return mail;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }




    PersonDataEntity(Long id, String name, String firstname, String mail, String description, boolean isStudent, boolean isTeacher){
            this.id=id;
            this.name=name;
            this.firstname=firstname;
            this.mail=mail;
            this.description= description;
            this.isStudent=isStudent;
            this.isTeacher=isTeacher;
        }

         public PersonDataEntity getCopie(){
            return new PersonDataEntity(id, name, firstname, mail, description, isStudent, isTeacher);
        }
}
