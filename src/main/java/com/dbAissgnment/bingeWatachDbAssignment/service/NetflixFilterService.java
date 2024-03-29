package com.dbAissgnment.bingeWatachDbAssignment.service;

import com.dbAissgnment.bingeWatachDbAssignment.model.NetflixDataModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface NetflixFilterService {

    List<NetflixDataModel> getDataByType(List<NetflixDataModel> inputList, String type, Date fromDate, Date toDate, long n);

    List<NetflixDataModel> getDataByTitle(List<NetflixDataModel> inputList, String listedIn, Date fromDate, Date toDate, long n);

    List<NetflixDataModel> getDataByTypeAndCountry(List<NetflixDataModel> inputList, String type, String country, Date fromDate, Date toDate, long n);

    List<NetflixDataModel> getDataByDate(List<NetflixDataModel> inputList, Date fromDate, Date toDate, long n);

}
