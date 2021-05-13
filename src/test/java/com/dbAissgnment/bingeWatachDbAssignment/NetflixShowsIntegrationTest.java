package com.dbAissgnment.bingeWatachDbAssignment;

import com.dbAissgnment.bingeWatachDbAssignment.entity.NetflixRepository;
import com.dbAissgnment.bingeWatachDbAssignment.model.NetflixDataModel;
import com.dbAissgnment.bingeWatachDbAssignment.service.NetflixFilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(NetflixDataModel.class)
public class NetflixShowsIntegrationTest {

    @Autowired
    NetflixRepository netflixRepository;

    @Autowired
    NetflixFilterService netflixFilterService;

    @Test
    public void testFilterByName() {

        NetflixDataModel nf = new NetflixDataModel("1", "Show_Title", "Test Title",
                "Test director", "Test Cast", "Test", "21-01-2001", "200", "5", "5 seasons"
                , "Test Starts", "Test desc");
        netflixRepository.save(nf);

        List<NetflixDataModel> li = netflixFilterService.getDataByType(netflixRepository.findAll(), "Show_Title", null, null, 10);
        assertEquals(li.get(0).getTitle(), nf.getTitle());
    }
}
