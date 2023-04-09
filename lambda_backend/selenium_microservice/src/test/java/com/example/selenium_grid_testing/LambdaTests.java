package com.example.selenium_grid_testing;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaTests extends SeleniumHelper implements IExecutionListener{
    private static final Logger LOGGER = Logger.getLogger(LambdaTests.class.getName());
    // KafkaProducerConfig kafkaObj = new KafkaProducerConfig();
    // KafkaProducer<String, JsonNode> producer = kafkaObj.getKafkaProducer();     
    // ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void onExecutionStart(){
        System.out.println("onExecutionStart");
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setupTest(@Optional("remote-chrome") String browser)  throws InterruptedException, MalformedURLException{
        // Code to setup the RemoteWebDriver based on the passed in browser parameter
        if (browser.equalsIgnoreCase("remote-chrome")){
            setupThread("remote-chrome");
        }
        else if (browser.equalsIgnoreCase("remote-firefox")){
            setupThread("remote-firefox");
        }
    }
    
    @Test
    @Parameters({"browser"})
    public void produceLogsToKafka() throws InterruptedException{
        // Extracting links from lambdatest website
        WebDriver webdriver = getDriver();
        webdriver.navigate().to("https://www.google.com");
        webdriver.manage().window().maximize();
        
        LOGGER.info("hello chala abh ?");

        if (getDriver() != null)
        {
            tearDownDriver();
        }
        // JsonNode jsonNode = objectMapper.createObjectNode().put("url", title);
        // LOGGER.info("Object created -->" + jsonNode.toString());

        // ProducerRecord<String, JsonNode> record =  new ProducerRecord<String,JsonNode>("networkLogs", kafkaObj.clientID+".chrome", jsonNode);
        // producer.send(record, new Callback() {
        //     @Override
        //     public void onCompletion(RecordMetadata metadata, Exception exception) {
        //         if (exception != null) {
        //             System.out.println("Error sending message to Kafka");
        //             exception.printStackTrace();
        //         } else {
        //             System.out.printf("Message sent to topic %s at offset %d%n", metadata.topic(), metadata.offset());
        //         }
        //     }
        // });
    }

    @AfterClass
    public void teardownTest() {
        // Code to quit the RemoteWebDriver
        tearDownDriver();
    }

    @Override
    public void onExecutionFinish()
    {
        System.out.println("onExecutionFinish");
    }
}

