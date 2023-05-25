package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface courseRepository extends JpaRepository<course, String> {
    List<course> findBycourseid(String courseid);

    List<course> findByCourseidIn(List<String> courseids);

    List<course> findByRoomid(String roomid);

    @Query("SELECT c FROM course c WHERE c.roomid = :roomid and ((c.active = true AND c.regular_weekday = :today) OR c.schedule_date = :date ) and c.regular_starttime <= :current and c.regular_endtime >= :current")
    List<course> getCurrentCourse(String roomid, String today, LocalDate date, LocalTime current);

    @Query("SELECT c FROM course c WHERE c.courseid <> :id AND c.roomid = :roomid and (c.active = true AND c.regular_weekday = :weekday) AND c.regular_starttime <= :start AND c.regular_endtime >= :start AND c.regular_starttime <= :end AND c.regular_endtime >= :end ORDER BY c.courseid")
    List<course> getSameCourse(String id ,String roomid, String weekday, LocalTime start, LocalTime end);



}
