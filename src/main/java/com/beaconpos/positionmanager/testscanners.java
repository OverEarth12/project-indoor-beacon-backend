package com.beaconpos.positionmanager;

public class testscanners {
    private String scannerid;
    private String roomid;

    public testscanners(String scannerid, String roomid) {
        this.scannerid = scannerid;
        this.roomid = roomid;
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
}
