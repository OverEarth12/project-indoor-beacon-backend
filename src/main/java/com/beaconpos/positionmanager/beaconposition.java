package com.beaconpos.positionmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class beaconposition {
    @Id
    private String uuid;
    private String roomid;
    private Integer rssi1;
    private Integer rssi2;
    private Integer rssi3;

    public beaconposition() {
    }

    public beaconposition(String uuid, String roomid) {
        this.uuid = uuid;
        this.roomid = roomid;
    }

    public beaconposition(String uuid, String roomid, int slot, int newRssi) {
        this.uuid = uuid;
        this.roomid = roomid;
        setRssiBySlot(slot, newRssi);
    }

    public Integer getRssiBySlot(int slot){
        return slot == 1 ? this.rssi1 : slot == 2 ? this.rssi2 : slot == 3 ? this.rssi3 : 0;
    }

    public void setRssiBySlot(int slot, Integer newRssi){
        if(slot == 1){
            this.rssi1 = newRssi;
        }else if(slot == 2){
            this.rssi2 = newRssi;
        }else if(slot == 3){
            this.rssi3 = newRssi;
        }

        if((this.rssi1 == null && this.rssi2 == null) && this.rssi3 == null){
            this.roomid = null;
        }
    }

    public boolean isOnline(){
        return ((rssi1 != null && rssi2 != null) && rssi3 != null);
    }

    public void copyRssi(beaconposition bp){
        setRssi1(bp.getRssi1());
        setRssi2(bp.getRssi2());
        setRssi3(bp.getRssi3());
    }

    public void markAsNotFound(){
        setRoomid(null);
        setRssi1(null);
        setRssi2(null);
        setRssi3(null);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setRssi1(Integer rssi1) {
        this.rssi1 = rssi1;
    }

    public Integer getRssi2() {
        return rssi2;
    }

    public void setRssi2(Integer rssi2) {
        this.rssi2 = rssi2;
    }

    public Integer getRssi3() {
        return rssi3;
    }

    public void setRssi3(Integer rssi3) {
        this.rssi3 = rssi3;
    }

    @Override
    public String toString(){
        if(roomid == null || (rssi3 == null || (rssi1 == null || rssi2 == null))){
            return "(uuid"+this.uuid+" is not in area.";
        }
        return "(uuid: "+this.uuid+", room: "+this.roomid+", rss1: "+this.rssi1+", rss2: "+this.rssi2+", rss3: "+this.rssi3+")";
    }
}
