package com.beaconpos.positionmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class participation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int participation_id;
    private String roomid;
    private String user_id;
    private int position_x;
    private int position_y;
    private int record_id;

    public participation() {
    }

    public participation(String roomid, String user_id, int position_x, int position_y, int record_id) {
        this.roomid = roomid;
        this.user_id = user_id;
        this.position_x = position_x;
        this.position_y = position_y;
        this.record_id = record_id;
    }

    public int getParticipation_id() {
        return participation_id;
    }

    public void setParticipation_id(int participation_id) {
        this.participation_id = participation_id;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }
}
