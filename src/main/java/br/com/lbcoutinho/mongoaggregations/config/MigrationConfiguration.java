package br.com.lbcoutinho.mongoaggregations.config;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrationConfiguration {

    @Bean
    public SpringBootMongock mongock(ApplicationContext springContext, MongoClient mongoClient) {
        return new SpringBootMongockBuilder(mongoClient, "mongo-aggregations", "br.com.lbcoutinho.mongoaggregations.migration")
                .setApplicationContext(springContext)
                .setLockQuickConfig()
                .build();
    }

}
