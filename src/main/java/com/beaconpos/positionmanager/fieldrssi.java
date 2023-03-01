package com.beaconpos.positionmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class fieldrssi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int fieldid;
    private int positionx;
    private int positiony;
    private String roomid;
    private Integer rssi1;
    private Integer rssi2;
    private Integer rssi3;

    public fieldrssi(){}

    public fieldrssi(int positionx, int positiony, String roomid, int slot, int rssi) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.roomid = roomid;
        setRssiBySlot(slot, rssi);
    }

    public fieldrssi(int fieldid, int positionx, int positiony, String roomid, Integer rssi1, Integer rssi2, Integer rssi3) {
        this.fieldid = fieldid;
        this.positionx = positionx;
        this.positiony = positiony;
        this.roomid = roomid;
        this.rssi1 = rssi1;
        this.rssi2 = rssi2;
        this.rssi3 = rssi3;
    }

    public void setRssiBySlot(int slot, int newRssi){
        if(slot == 1){
            this.rssi1 = newRssi;
        }else if(slot == 2){
            this.rssi2 = newRssi;
        }else if(slot == 3){
            this.rssi3 = newRssi;
        }
    }

    public Integer getRssiBySlot(int slot){
        if(slot == 1){
            return rssi1;
        }else if(slot == 2){
            return rssi2;
        }else if(slot == 3){
            return rssi3;
        }
        return 0;
    }

    public int getFieldid() {
        return fieldid;
    }

    public void setFieldid(int fieldid) {
        this.fieldid = fieldid;
    }

    public int getPositionx() {
        return positionx;
    }

    public void setPositionx(int positionx) {
        this.positionx = positionx;
    }

    public int getPositiony() {
        return positiony;
    }

    public void setPositiony(int positiony) {
        this.positiony = positiony;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public Integer getRssi1() {
        return rssi1;
    }

    public void setRssi1(int rssi1) {
        this.rssi1 = rssi1;
    }

    public Integer getRssi2() {
        return rssi2;
    }

    public void setRssi2(int rssi2) {
        this.rssi2 = rssi2;
    }

    public Integer getRssi3() {
        return rssi3;
    }

    public void setRssi3(int rssi3) {
        this.rssi3 = rssi3;
    }
}
