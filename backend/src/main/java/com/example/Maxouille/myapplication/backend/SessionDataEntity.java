package com.example.Maxouille.myapplication.backend;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 import javax.persistence.Entity;

 /**
 * Created by Maximilien on 09.01.2016.
 */

@Entity
public class SessionDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        int cours;
        int room;
        Date start;
        Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCours() {
        return cours;
    }

    public void setCours(int cours) {
        this.cours = cours;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    SessionDataEntity(Long id,int coursId,  int roomId, Date start, Date end){
            this.id=id;
            this.cours=coursId;
            this.room=roomId;
            this.start=start;
            this.end=end;
        }

    public SessionDataEntity getCopie(){
            return new SessionDataEntity(id, cours, room,  start, end);
        }

}
