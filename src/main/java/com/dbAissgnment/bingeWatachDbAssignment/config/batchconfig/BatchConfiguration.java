package com.dbAissgnment.bingeWatachDbAssignment.config.batchconfig;

import com.dbAissgnment.bingeWatachDbAssignment.listner.JobCompletionNotificationListener;
import com.dbAissgnment.bingeWatachDbAssignment.model.NetflixDataModel;
import com.dbAissgnment.bingeWatachDbAssignment.processor.NetflixItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

import static com.dbAissgnment.bingeWatachDbAssignment.commons.Constants.PATTERN;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;


    @Bean
    public FlatFileItemReader<NetflixDataModel> readerFromCsv() {
        FlatFileItemReader<NetflixDataModel> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new ClassPathResource("netflix_titles.csv"));
        return itemReader;
    }


    @Bean
    public LineMapper<NetflixDataModel> lineMapper() {
        DefaultLineMapper<NetflixDataModel> lineMapper = new DefaultLineMapper<NetflixDataModel>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(PATTERN);
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("show_id", "type", "title", "director", "cast", "country", "date_added",
                "release_year", "rating", "duration", "listed_in", "description");
        lineTokenizer.setIncludedFields(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        BeanWrapperFieldSetMapper<NetflixDataModel> fieldSetMapper = new BeanWrapperFieldSetMapper<NetflixDataModel>();
        fieldSetMapper.setTargetType(NetflixDataModel.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public ItemProcessor<NetflixDataModel, NetflixDataModel> processorForDb() {
        return new NetflixItemProcessor();
    }

    @Bean
    public NetflixItemProcessor processor() {
        return new NetflixItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<NetflixDataModel> writerToDb() {
        JdbcBatchItemWriter<NetflixDataModel> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO netflix_shows " +
                "(show_id, type ,title,director,cast,country,date_added,release_year,rating,duration,listed_in,description) " +
                "VALUES (:show_id, :type , :title,:director, :cast, :country, :date_added, :release_year, :rating, :duration, :listed_in, :description)");

        writer.setDataSource(this.dataSource);
        return writer;
    }

    @Bean
    public Job importCsvDataJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importCsvDataJob")
                .incrementer(new RunIdIncrementer())
                //.listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<NetflixDataModel, NetflixDataModel>chunk(200)
                .reader(readerFromCsv())
                .writer(writerToDb())
                .build();
    }
}
