package com.beaconpos.positionmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class roomscanner {
    @Id
    private String roomid;
    private String scannerid1;
    private String scannerid2;
    private String scannerid3;

    public roomscanner(String roomid, String scannerid1) {
        this.roomid = roomid;
        this.scannerid1 = scannerid1;
    }

    public roomscanner(String roomid, String scannerid1, String scannerid2, String scannerid3) {
        this.roomid = roomid;
        this.scannerid1 = scannerid1;
        this.scannerid2 = scannerid2;
        this.scannerid3 = scannerid3;
    }

    public roomscanner() {

    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getScannerid1() {
        return scannerid1;
    }

    public void setScannerid1(String scannerid1) {
        this.scannerid1 = scannerid1;
    }

    public String getScannerid2() {
        return scannerid2;
    }

    public void setScannerid2(String scannerid2) {
        this.scannerid2 = scannerid2;
    }

    public String getScannerid3() {
        return scannerid3;
    }

    public void setScannerid3(String scannerid3) {
        this.scannerid3 = scannerid3;
    }
}
