package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface participationRepository extends JpaRepository<participation, Integer> {
    List<fieldrssi> findById(int participation_id);
}
