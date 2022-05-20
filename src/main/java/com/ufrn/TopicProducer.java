package com.ufrn;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Scanner;

import javax.jms.*;

public class TopicProducer implements Runnable {
    ActiveMQConnectionFactory connectionFactory = null;

    public TopicProducer(ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run() {
        while (true) {
            try {

                // First create a connection
                Connection connection = connectionFactory.createConnection();
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                // Let's create a topic. If the topic exist, it will return that
                Destination destination = session.createTopic("CLIMATE");
                // Create a MessageProducer from the Session to the Topic or Queue
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);

                // Create a messages for the current climate
                Scanner scanner = new Scanner(System.in);
                String mensagem = scanner.nextLine();
                TextMessage message = session.createTextMessage(mensagem);
                producer.send(message);
                // Send the message to topic
                // Do the cleanup

            } catch (JMSException jmse) {
                System.out.println("Exception: " + jmse.getMessage());
            }
        }

    }
}