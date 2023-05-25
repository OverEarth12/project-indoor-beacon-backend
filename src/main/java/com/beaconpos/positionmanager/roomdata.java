package com.beaconpos.positionmanager;

import java.time.LocalDateTime;

public class roomdata {
    private String roomid;
    private LocalDateTime currentTime;

    public roomdata() {
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }
}
