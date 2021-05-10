package com.dbAissgnment.BingeWatachDbAssignment.controller;

import com.dbAissgnment.BingeWatachDbAssignment.aspectj.TrackExecutionTime;
import com.dbAissgnment.BingeWatachDbAssignment.entity.NetflixRepository;
import com.dbAissgnment.BingeWatachDbAssignment.model.NetflixDataModel;
import com.dbAissgnment.BingeWatachDbAssignment.service.NetflixFilterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
@Slf4j

public class NetflixDataController {

    @Autowired
    NetflixRepository netflixRepository;
    @Autowired
    NetflixFilterService netflixFilterService;

    @PostMapping("/netflix/add")
    public ResponseEntity<String> createData(@RequestBody NetflixDataModel netflixDataModel) {
        netflixRepository.save(netflixDataModel);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("{show_Id}")
                .buildAndExpand(netflixDataModel.getShow_id()).toUri();
        return ResponseEntity.ok().body("Create User");
    }

    @GetMapping(path = "/netflix/getShows")
    @TrackExecutionTime
    public @ResponseBody
    Iterable<NetflixDataModel> getAllUsers() {
        return netflixRepository.findAll();
    }


    @GetMapping(path = "netflix/getShowsByTitle/{title}")
    public @ResponseBody
    Iterable<NetflixDataModel> getUsersByTitle(@PathVariable String title) {
        return netflixFilterService.getDataByType(netflixRepository.findAll(), title, null, null, 10);

    }

    @GetMapping(path = "netflix/getShowsByCountry/{country}")
    public @ResponseBody
    Iterable<NetflixDataModel> getUsersByCountry(@PathVariable String country) {
        return netflixFilterService.getDataByTypeAndCountry(netflixRepository.findAll(), country, country, null, null, 10);

    }
    @GetMapping(path = "netflix/getShowsBetweenDates/{fromDate}&{toDate}")
    public @ResponseBody
    Iterable<NetflixDataModel> getUsersByCountry(@RequestBody Date fromDate, Date toDate) {
        return netflixFilterService.getDataByDate(netflixRepository.findAll(), fromDate, toDate, 10);

    }
}
