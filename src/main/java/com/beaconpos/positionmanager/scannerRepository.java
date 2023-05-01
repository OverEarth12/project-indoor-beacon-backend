package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface scannerRepository extends JpaRepository<scanner, String> {
    List<scanner> findByScannerid(String scannerid);
}
