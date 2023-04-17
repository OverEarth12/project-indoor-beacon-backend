package com.beaconpos.positionmanager;

public class beaconrssi {
    private String uuid;
    private int rssi;

    public beaconrssi() {
    }

    public beaconrssi(String uuid, int rssi) {
        this.uuid = uuid;
        this.rssi = rssi;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
