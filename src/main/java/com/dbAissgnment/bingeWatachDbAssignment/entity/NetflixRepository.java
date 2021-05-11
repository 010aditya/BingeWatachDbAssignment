package com.dbAissgnment.bingeWatachDbAssignment.entity;

import com.dbAissgnment.bingeWatachDbAssignment.model.NetflixDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetflixRepository extends JpaRepository<NetflixDataModel,Long> {
}
