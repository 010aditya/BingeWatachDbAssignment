package com.dbAissgnment.BingeWatachDbAssignment.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class NetflixDbtoCsvJobListener extends JobExecutionListenerSupport {

/*
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NetflixDbtoCsvJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");*/
/*
            List<NetflixDataModel> results = jdbcTemplate.query("SELECT show_id, type ,title,director,cast,country,date_added,release_year,rating,duration,listed_in,description FROM netflix_shows", new RowMapper<NetflixDataModel>() {
                @Override
                public NetflixDataModel mapRow(ResultSet rs, int row) throws SQLException {
                    return new NetflixDataModel(rs.getLong(0), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
                            , rs.getString(6), rs.getString(7), rs.getString(8)
                            , rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
                }
            });

            for (NetflixDataModel person : results) {
                log.info("Found <" + person + "> in the database.");
            }*/


}
