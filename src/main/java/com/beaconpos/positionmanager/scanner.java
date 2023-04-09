package com.beaconpos.positionmanager;

public class scanner {
    private String scannerid;
    private String model;
    private String os;
    private double bluetooh;

    public scanner(String scannerid, String model, String os, double bluetooh) {
        this.scannerid = scannerid;
        this.model = model;
        this.os = os;
        this.bluetooh = bluetooh;
    }

    public String getScannerid() {
        return scannerid;
    }

    public void setScannerid(String scannerid) {
        this.scannerid = scannerid;
    }
}
