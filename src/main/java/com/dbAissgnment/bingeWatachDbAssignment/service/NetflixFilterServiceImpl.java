package com.dbAissgnment.bingeWatachDbAssignment.service;

import com.dbAissgnment.bingeWatachDbAssignment.commons.DateUtil;
import com.dbAissgnment.bingeWatachDbAssignment.model.NetflixDataModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NetflixFilterServiceImpl implements NetflixFilterService {
    @Override
    public List<NetflixDataModel> getDataByType(List<NetflixDataModel> inputList, String type, Date fromDate, Date toDate, long n) {
        return inputList.stream()
                .filter(x -> x.getType() != null /*&& x.getReleaseYear() != null*/)
                //.filter(x -> x.getReleaseYear().after(fromDate) && x.getReleaseYear().before(toDate))
                .filter(x -> x.getType().equalsIgnoreCase(type)).limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public List<NetflixDataModel> getDataByTitle(List<NetflixDataModel> inputList, String listedIn, Date fromDate, Date toDate, long n) {
        return inputList.stream()
                .filter(x -> x.getTitle() != null)
                .filter(x -> x.getTitle().equalsIgnoreCase(listedIn))
                .limit(n).collect(Collectors.toList());
    }

    @Override
    public List<NetflixDataModel> getDataByTypeAndCountry(List<NetflixDataModel> inputList, String type, String country, Date fromDate, Date toDate, long n) {
        return inputList.stream()
                .filter(x -> x.getCountry() != null)
                .filter(x -> x.getCountry().equalsIgnoreCase(country))
                .limit(n).collect(Collectors.toList());
    }

    @Override
    public List<NetflixDataModel> getDataByDate(List<NetflixDataModel> inputList, Date fromDate, Date toDate, long n) {
        return inputList.stream()
                .filter(x -> x.getDate_added() != null)
                 .filter(x->x.getDate().after(fromDate) && x.getDate().before(toDate))
                .limit(n)
                .collect(Collectors.toList());
    }
}
