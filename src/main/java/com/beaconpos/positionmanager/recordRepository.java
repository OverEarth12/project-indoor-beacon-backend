package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface recordRepository extends JpaRepository<recordclass, Integer> {
    List<recordclass> findById(int record_id);
}
