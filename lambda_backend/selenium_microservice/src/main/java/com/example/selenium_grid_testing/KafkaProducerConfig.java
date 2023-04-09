package com.example.selenium_grid_testing;

import java.util.Properties;

import java.util.UUID;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;

public class KafkaProducerConfig {
    private static final Logger LOGGER = Logger.getLogger(KafkaProducerConfig.class.getName());
    String clientID;
    
    KafkaProducer<String, JsonNode> getKafkaProducer(){
        this.clientID = UUID.randomUUID().toString();
        LOGGER.info("Setting up kafka producer" + this.clientID);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        KafkaProducer<String, JsonNode> producer = new KafkaProducer<>(props);
        return producer;
    }
}
