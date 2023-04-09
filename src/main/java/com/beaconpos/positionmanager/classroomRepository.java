package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface classroomRepository extends JpaRepository<classroom, String> {
    List<classroom> findByRoomid(String roomid);
}
