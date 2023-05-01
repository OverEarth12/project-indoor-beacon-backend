package com.beaconpos.positionmanager;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "course")
public class course {
    @Id
    private String courseid;
    private String course_name;
    private String description;
    private String regular_weekday;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime regular_starttime;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime regular_endtime;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date schedule_date;
    private String roomid;
    private boolean active;

    public course(String courseid, String course_name, String description, String regular_weekday, LocalTime regular_starttime, LocalTime regular_endtime, String roomid, boolean active) {
        this.courseid = courseid;
        this.course_name = course_name;
        this.description = description;
        this.regular_weekday = regular_weekday;
        this.regular_starttime = regular_starttime;
        this.regular_endtime = regular_endtime;
        this.roomid = roomid;
        this.active = active;
    }

    public course(String courseid, String course_name, String description, LocalTime regular_starttime, LocalTime regular_endtime, Date schedule_date, boolean active) {
        this.courseid = courseid;
        this.course_name = course_name;
        this.description = description;
        this.regular_starttime = regular_starttime;
        this.regular_endtime = regular_endtime;
        this.schedule_date = schedule_date;
        this.active = active;
    }

    public course() {
    }

    public String getCourseId() {
        return courseid;
    }

    public void setCourseId(String courseid) {
        this.courseid = courseid;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegular_weekday() {
        return regular_weekday;
    }

    public void setRegular_weekday(String regular_weekday) {
        this.regular_weekday = regular_weekday;
    }

    public LocalTime getRegular_starttime() {
        return regular_starttime;
    }

    public void setRegular_starttime(LocalTime regular_starttime) {
        this.regular_starttime = regular_starttime;
    }

    public LocalTime getRegular_endtime() {
        return regular_endtime;
    }

    public void setRegular_endtime(LocalTime regular_endtime) {
        this.regular_endtime = regular_endtime;
    }

    public Date getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(Date schedule_date) {
        this.schedule_date = schedule_date;
    }

    @Override
    public String toString(){
        return courseid;
    }
}
