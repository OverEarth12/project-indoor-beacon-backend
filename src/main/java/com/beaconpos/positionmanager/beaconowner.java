package com.beaconpos.positionmanager;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class beaconowner {
    @Id
    private String user_id;
    private String uuid;
    @Column(name = "start_timestamp")
    private LocalDateTime starttimestamp;
    @Column(name = "return_timestamp")
    private LocalDateTime returntimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid", insertable = false, updatable = false)
    private beaconposition beacon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private beaconowner ownedby;

    public beaconowner() {
    }

    public beaconowner(String user_id, String uuid, LocalDateTime starttimestamp, LocalDateTime returntimestamp) {
        this.user_id = user_id;
        this.uuid = uuid;
        this.starttimestamp = starttimestamp;
        this.returntimestamp = returntimestamp;
    }

    @Override
    public String toString(){
        return this.user_id+" owned "+this.uuid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getStarttimestamp() {
        return starttimestamp;
    }

    public void setStarttimestamp(LocalDateTime starttimestamp) {
        this.starttimestamp = starttimestamp;
    }

    public LocalDateTime getReturntimestamp() {
        return returntimestamp;
    }

    public void setReturntimestamp(LocalDateTime returntimestamp) {
        this.returntimestamp = returntimestamp;
    }
}
