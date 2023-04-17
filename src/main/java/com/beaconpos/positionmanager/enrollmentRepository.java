package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface enrollmentRepository extends JpaRepository<enrollment, String> {
    List<enrollment> findByenrollid(String enrollid);

    List<enrollment> findByuseridAndCourseidAndEnddate(String userid, String courseid, LocalDateTime enddate);

    @Query("SELECT e FROM enrollment e JOIN users u ON e.userid = u.user_id WHERE u.role = 'Student' and e.courseid = ?1 and e.enddate IS NULL")
    List<enrollment> findenrollmentOfStudent(String courseid);
}
