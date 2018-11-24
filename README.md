# spring-kafka

## Installation
 - zookeeper download (https://zookeeper.apache.org/)
 - kafka download (https://kafka.apache.org/quickstart)

## Set up
 - zookeeper server start  
 `${ZOOKEEPER_HOME}/bin/zkServer.sh start`
 - kafka broker start  
 `${KAFKA_HOME}/bin/kafka-server-start.sh -daemon ${KAFKA_HOME}/config/server.properties`
 
 ## Create Topic
  `${KAFKA_HOME}/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic rabin`
  
 ## Publisher
  - start PublisherApplication main
  
 ## Subscriber
   - start SubscriberApplication main