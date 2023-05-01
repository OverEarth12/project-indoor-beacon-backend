package com.beaconpos.positionmanager;

public class positionResult {
    private String user_id;
    private String name;
    private String role;
    private String uuid;
    private int positionX;
    private int positionY;

    public positionResult() {
    }

    public positionResult(String user_id, String name, String role, String uuid, int positionX, int positionY) {
        this.user_id = user_id;
        this.name = name;
        this.role = role;
        this.uuid = uuid;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    //    private users owner;
//    private userposition position;
//
//    public positionResult(users owner, userposition position) {
//        this.owner = owner;
//        this.position = position;
//    }
//
//    public users getOwner() {
//        return owner;
//    }
//
//    public void setOwner(users owner) {
//        this.owner = owner;
//    }
//
//    public userposition getPosition() {
//        return position;
//    }
//
//    public void setPosition(userposition position) {
//        this.position = position;
//    }
}
