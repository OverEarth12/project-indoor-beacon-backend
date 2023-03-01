package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface roomscannerRepository extends JpaRepository<roomscanner, String> {
    List<roomscanner> findByRoomid(String roomid);
}
