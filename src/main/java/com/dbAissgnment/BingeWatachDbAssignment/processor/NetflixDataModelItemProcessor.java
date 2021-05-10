package com.dbAissgnment.BingeWatachDbAssignment.processor;

import com.dbAissgnment.BingeWatachDbAssignment.model.NetflixDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class NetflixDataModelItemProcessor implements ItemProcessor<NetflixDataModel, NetflixDataModel> {
    private static final Logger log = LoggerFactory.getLogger(NetflixDataModelItemProcessor.class);

    @Override
    public NetflixDataModel process(final NetflixDataModel netflixDataModel) throws Exception {
      /*  final String firstName = NetflixDataModel.getFirstName().toUpperCase();
        final String lastName = NetflixDataModel.getLastName().toUpperCase();
*/
        final NetflixDataModel transformedPerson = new NetflixDataModel(netflixDataModel.getShow_id(), netflixDataModel.getType(), netflixDataModel.getTitle(), netflixDataModel.getDirector(), netflixDataModel.getCast(), netflixDataModel.getCountry(), netflixDataModel.getDate_added()
                , netflixDataModel.getRelease_year()
                , netflixDataModel.getRating(), netflixDataModel.getDuration(), netflixDataModel.getListed_in(), netflixDataModel.getDescription());

        log.info("Converting (" + netflixDataModel + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
