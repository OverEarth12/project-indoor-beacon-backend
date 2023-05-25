package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface usersRepository extends JpaRepository<users, String> {
    @Query("select u from users u JOIN beaconowner bo ON u.user_id = bo.userid JOIN beaconposition bp ON bp.uuid = bo.uuid WHERE bp.roomid = ?1 AND bo.returntimestamp IS NULL")
    List<users> finduserinroom(String roomid);

    @Query("select u from users u JOIN enrollment e ON u.user_id = e.userid WHERE e.courseid = ?1 AND e.enddate IS NULL ")
    List<users> finduserincourse(String courseid);

    @Query("select u from users u where u.user_id = ?1")
    List<users> findbyuserid(String user_id);
}
