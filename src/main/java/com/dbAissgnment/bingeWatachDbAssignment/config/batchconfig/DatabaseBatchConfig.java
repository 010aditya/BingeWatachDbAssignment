package com.dbAissgnment.bingeWatachDbAssignment.config.batchconfig;

/*@Configuration
@EnableBatchProcessing*/
public class DatabaseBatchConfig {


/*
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
    public NetflixItemProcessor processorFromDatabase() {
        return new NetflixItemProcessor();
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
    }*/
}
