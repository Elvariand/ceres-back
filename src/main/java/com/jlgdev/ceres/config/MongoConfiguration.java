package com.jlgdev.ceres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration {

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://opis:opis587516SATURN@ceres-cluster.s5d8s.mongodb.net/?retryWrites=true&w=majority&appName=ceres-cluster");
    }

    @Bean
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "ceres-mongodb");
    }

}
