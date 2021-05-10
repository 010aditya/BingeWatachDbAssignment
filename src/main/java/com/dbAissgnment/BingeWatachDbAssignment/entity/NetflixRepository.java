package com.dbAissgnment.BingeWatachDbAssignment.entity;

import com.dbAissgnment.BingeWatachDbAssignment.model.NetflixDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetflixRepository extends JpaRepository<NetflixDataModel,Long> {
}
