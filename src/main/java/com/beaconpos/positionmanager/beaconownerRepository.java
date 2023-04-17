package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface beaconownerRepository extends JpaRepository<beaconowner, String> {
    @Query("SELECT bo from beaconowner bo JOIN beaconposition bp ON bo.uuid = bp.uuid WHERE bp.roomid = ?1 and bo.returntimestamp IS NULL")
    List<beaconowner> findbyRoomId(String roomid);
}
