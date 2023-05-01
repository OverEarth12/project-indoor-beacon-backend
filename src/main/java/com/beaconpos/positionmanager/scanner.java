package com.beaconpos.positionmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class scanner {
    @Id
    private String scannerid;
    private String model;
    private String os;
    private double bluetooth;

    public scanner(String scannerid, String model, String os, double bluetooh) {
        this.scannerid = scannerid;
        this.model = model;
        this.os = os;
        this.bluetooth = bluetooh;
    }

    public scanner() {

    }

    public String getScannerid() {
        return scannerid;
    }

    public void setScannerid(String scannerid) {
        this.scannerid = scannerid;
    }
}
