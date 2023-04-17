package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface courseRepository extends JpaRepository<course, String> {
    List<course> findBycourseid(String courseid);
}
