package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface courseownerRepository extends JpaRepository<courseowner, Integer> {

    List<courseowner> findByUserid(String userid);

    List<courseowner> findByCourseid(String courseid);

    List<courseowner> findByCourseidAndUserid(String courseid, String userid);
}
