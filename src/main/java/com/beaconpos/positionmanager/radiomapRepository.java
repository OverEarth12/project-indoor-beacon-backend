package com.beaconpos.positionmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface radiomapRepository extends JpaRepository<radiomap, Integer> {
    List<radiomap> findByRoomid(String roomid);
}
