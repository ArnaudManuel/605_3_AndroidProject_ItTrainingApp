package com.example.Maxouille.myapplication.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Maximilien on 09.01.2016.
 */

@Entity
public class CoursDataEntity {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name="";
    String description="";

    CoursDataEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    CoursDataEntity(Long id, String name, String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }


    public CoursDataEntity getCopie(){
            return new CoursDataEntity(id,name,description);
        }
}
