package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface usersRepository extends JpaRepository<users, String> {
    @Query("select u from users u JOIN beaconowner bo ON u.user_id = bo.user_id JOIN beaconposition bp ON bp.uuid = bo.uuid WHERE bp.roomid = ?1 AND bo.returntimestamp IS NULL")
    List<users> finduserinroom(String roomid);
}
