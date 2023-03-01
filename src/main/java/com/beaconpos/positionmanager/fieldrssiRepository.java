package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface fieldrssiRepository extends JpaRepository<fieldrssi, Integer> {

    List<fieldrssi> findByRoomid(String roomid);

}
