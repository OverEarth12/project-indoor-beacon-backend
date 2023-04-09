package com.beaconpos.positionmanager;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;


@Data
@Entity
public class radiomap {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String roomid;
    private String scannerid;
    private int rssi;
//    private Integer positionx;
//    private Integer positiony;
    private String uuid;

    public radiomap(){}

    public radiomap(String roomid, String scannerid, int rssi, String uuid){
        this.roomid = roomid;
        this.scannerid = scannerid;
        this.rssi = rssi;
        this.uuid = uuid;
    }

    public radiomap(String roomid, String scannerid, int rssi, int positionx, int positiony) {
        this.roomid = roomid;
        this.scannerid = scannerid;
        this.rssi = rssi;
//        this.positionx = positionx;
//        this.positiony = positiony;
    }

    public radiomap(int id, String roomid, String scannerid, int rssi, int positionx, int positiony) {
        this.id = id;
        this.roomid = roomid;
        this.scannerid = scannerid;
        this.rssi = rssi;
//        this.positionx = positionx;
//        this.positiony = positiony;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomid() {
        return roomid;
    }

    public String getScannerid() {
        return scannerid;
    }

    public int getRssi() {
        return rssi;
    }

    public int getPositionx() {
        return 0;
    }

    public int getPositiony() {
        return 0;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
