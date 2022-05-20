package com.ufrn;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
  public static void main(String[] args) {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

    // Create the consumer. It will wait to listen to the Topic
    Thread topicConsumerThread = new Thread(new TopicConsumer(connectionFactory));
    topicConsumerThread.start();
    System.out.println("servidor de pé");
  }
}
