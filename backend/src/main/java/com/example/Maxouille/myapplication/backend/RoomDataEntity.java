package com.example.Maxouille.myapplication.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Maximilien on 09.01.2016.
 */

@Entity
public class RoomDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    char[] name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    RoomDataEntity(Long id, String name){
            this.id=id;
            this.name= new char[]{name.charAt(0),name.charAt(1),name.charAt(2)};
        }

}
