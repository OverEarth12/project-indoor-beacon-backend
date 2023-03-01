package com.beaconpos.positionmanager;

public class userposition {
    private String uuid;
    private int positionX;
    private int positionY;

    public userposition() {
    }

    public userposition(String uuid, int positionX, int positionY) {
        this.uuid = uuid;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
