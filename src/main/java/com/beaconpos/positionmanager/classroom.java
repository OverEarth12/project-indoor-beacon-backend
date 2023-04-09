package com.beaconpos.positionmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class classroom {
    @Id
    private String roomid;
    private int floor;
    private String detail;
    private String scanner_1;
    private String scanner_2;
    private String scanner_3;

    public classroom(String roomid, int floor, String detail) {
        this.roomid = roomid;
        this.floor = floor;
        this.detail = detail;
    }

    public classroom() {}

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setScanner(int num, scanner reg_scanner){
        if(num == 1)
            scanner_1 = reg_scanner.getScannerid();
        else if(num == 2)
            scanner_2 = reg_scanner.getScannerid();
        else if(num == 3)
            scanner_3 = reg_scanner.getScannerid();
    }

    public String getScanner_1() {
        return scanner_1;
    }

    public String getScanner_2() {
        return scanner_2;
    }

    public String getScanner_3() {
        return scanner_3;
    }
}
