package com.ufrn;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
  public static void main(String[] args) {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

    Thread topicProducerThread = new Thread(new TopicProducer(connectionFactory));
    topicProducerThread.start();
    System.out.println("cliente de pé");
  }
}
