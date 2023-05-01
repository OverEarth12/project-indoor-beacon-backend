package com.beaconpos.positionmanager;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class beaconowner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int logid;
    @Column(name = "user_id")
    private String userid;
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
    private users ownedby;

    public beaconowner() {
    }

    public beaconowner(String user_id, String uuid) {
        this.userid = user_id;
        this.uuid = uuid;
        this.starttimestamp = LocalDateTime.now();
    }

    public beaconowner(String user_id, String uuid, LocalDateTime starttimestamp, LocalDateTime returntimestamp) {
        this.userid = user_id;
        this.uuid = uuid;
        this.starttimestamp = starttimestamp;
        this.returntimestamp = returntimestamp;
    }

    public void returnBeacon(){
        if(this.returntimestamp == null){
        this.returntimestamp = LocalDateTime.now();}
    }

    @Override
    public String toString(){
        return this.userid+" owned "+this.uuid;
    }

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String user_id) {
        this.userid = user_id;
    }

    public int getLogid() {
        return logid;
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
