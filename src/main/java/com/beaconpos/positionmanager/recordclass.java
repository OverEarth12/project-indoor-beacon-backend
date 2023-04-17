package com.beaconpos.positionmanager;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "record")
public class recordclass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int record_id;
    private String roomid;
    private LocalDateTime record_time;

    public recordclass(int recordid, String roomid) {
        this.record_id = recordid;
        this.roomid = roomid;
        record_time = LocalDateTime.now();
    }

    public recordclass(String roomid){
        this.roomid = roomid;
        record_time = LocalDateTime.now();
    }

    public recordclass() {
    }

    public int getRecordid() {
        return record_id;
    }

    public void setRecordid(int recordid) {
        this.record_id = recordid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
}
