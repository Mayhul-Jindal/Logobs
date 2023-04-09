package main

import (
	"log"
	"os"
	"time"

	"github.com/confluentinc/confluent-kafka-go/kafka"
)

func main() {
	p, err := kafka.NewProducer(&kafka.ConfigMap{
		"bootstrap.servers": "kafka-1:9092",
		"client.id":         "client-1",
		"acks":              "all",
	})

	if err != nil {
		log.Printf("Failed to create producer: %s\n", err)
		os.Exit(1)
	}
	topic := "network.logs"
	value := "hello"
	delivery_chan := make(chan kafka.Event, 10000)

	for{
		if err = p.Produce(
			&kafka.Message{
				TopicPartition: kafka.TopicPartition{Topic: &topic, Partition: kafka.PartitionAny},
				Value: []byte(value)},
				delivery_chan,
			); 
		err != nil{
			log.Fatal(err)
		}

		time.Sleep(time.Second)
	}
}
 