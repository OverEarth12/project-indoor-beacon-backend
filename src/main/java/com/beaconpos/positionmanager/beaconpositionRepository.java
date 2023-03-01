package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface beaconpositionRepository extends JpaRepository<beaconposition, String> {
    List<beaconposition> findByRoomid(String roomid);
}
