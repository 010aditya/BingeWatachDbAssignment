package com.dbAissgnment.bingeWatachDbAssignment.config.batchconfig;

import com.dbAissgnment.bingeWatachDbAssignment.model.NetflixDataModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NeflixDataModelRowMapper implements RowMapper<NetflixDataModel> {

        @Override
        public NetflixDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            NetflixDataModel NetflixDataModel = new NetflixDataModel();
            NetflixDataModel.setShow_id(rs.getString("show_id"));
            NetflixDataModel.setType(rs.getString("type"));
            NetflixDataModel.setTitle(rs.getString("title"));
            NetflixDataModel.setDirector(rs.getString("director"));
            NetflixDataModel.setCast(rs.getString("cast"));
            NetflixDataModel.setCountry(rs.getString("country"));
            NetflixDataModel.setDate_added(rs.getString("date_added"));
            NetflixDataModel.setRelease_year(rs.getString("release_year"));
            NetflixDataModel.setDuration(rs.getString("duration"));
            NetflixDataModel.setListed_in(rs.getString("listed_in"));
            NetflixDataModel.setDescription(rs.getString("description"));
            return NetflixDataModel;
        }

    }
