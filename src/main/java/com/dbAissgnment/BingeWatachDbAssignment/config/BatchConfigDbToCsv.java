package com.dbAissgnment.BingeWatachDbAssignment.config;

import com.dbAissgnment.BingeWatachDbAssignment.model.NetflixDataModel;
import com.dbAissgnment.BingeWatachDbAssignment.processor.NetflixDataModelItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
@Configuration
@EnableBatchProcessing
public class BatchConfigDbToCsv {



    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<NetflixDataModel> readFromDatabase() {
        JdbcCursorItemReader<NetflixDataModel> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT show_id, type ,title,director,cast," +
                "country,date_added,release_year,rating,duration,listed_in,description FROM netflix_shows");
        cursorItemReader.setRowMapper(new NeflixDataModelRowMapper());
        return cursorItemReader;
    }

    @Bean
    public NetflixDataModelItemProcessor processorFromDatabase() {
        return new NetflixDataModelItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<NetflixDataModel> writerToCsv() {
        FlatFileItemWriter<NetflixDataModel> writer = new FlatFileItemWriter<>();
        writer.setResource(new ClassPathResource("netflix_titles_withint.csv"));

        DelimitedLineAggregator<NetflixDataModel> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<NetflixDataModel> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"show_id", "type", "title", "director", "cast", "country", "date_added",
                "release_year", "rating", "duration", "listed_in", "description"});
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<NetflixDataModel, NetflixDataModel>chunk(200)
                .reader(readFromDatabase())
              //  .processor(processorFromDatabase())
                .writer(writerToCsv()).build();
    }

    @Bean
    public Job exportPerosnJob() {
        return jobBuilderFactory.get("exportPeronJob")
                .incrementer(new RunIdIncrementer())
                .flow(step()).end().build();
    }
}
