package com.beaconpos.positionmanager;

import java.util.HashMap;
import java.util.List;

public class scannerResult {
    private String scannerid;
    private String roomid;
    private HashMap<String, Integer> beaconList;

    public scannerResult() {
    }

    public String getScannerid() {
        return scannerid;
    }

    public void setScannerid(String scannerid) {
        this.scannerid = scannerid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public HashMap<String, Integer> getBeaconList() {
        return beaconList;
    }

    public void setBeaconList(HashMap<String, Integer> beaconList) {
        this.beaconList = beaconList;
    }
}
