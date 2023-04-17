package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface beaconpositionRepository extends JpaRepository<beaconposition, String> {
    List<beaconposition> findByRoomid(String roomid);

    @Query("SELECT bp from beaconposition bp WHERE bp.uuid NOT IN (SELECT uuid from beaconowner WHERE returntimestamp IS NULL)")
    List<beaconposition> findNotOwnedBeacons();

    @Query("SELECT bp from beaconposition bp WHERE bp.roomid = ?1 OR bp.uuid IN ?2")
    List<beaconposition> findByRoomidandids(String roomid, String[] ids);
}
