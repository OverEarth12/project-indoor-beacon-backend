package com.beaconpos.positionmanager;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int enrollid;
    @Column(name = "user_id")
    private String userid;
    @Column(name = "course_id")
    private String courseid;
    @Column(name = "start_date")
    private LocalDateTime startdate;
    @Column(name = "end_date")
    private LocalDateTime enddate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private users user;

    @PrePersist
    protected void onCreate() {
        startdate = LocalDateTime.now();
    }

    public enrollment(String userid, String courseid) {
        this.userid = userid;
        this.courseid = courseid;
        this.startdate = LocalDateTime.now();
    }

    public enrollment() {
    }

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String userid) {
        this.userid = userid;
    }

    public String getCourse_id() {
        return courseid;
    }

    public void setCourse_id(String course_id) {
        this.courseid = course_id;
    }

    public LocalDateTime getStart_date() {
        return startdate;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.startdate = start_date;
    }

    public LocalDateTime getEnd_date() {
        return enddate;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.enddate = end_date;
    }

    @Override
    public String toString(){
        return enrollid+" "+userid+" "+courseid;
    }
}
