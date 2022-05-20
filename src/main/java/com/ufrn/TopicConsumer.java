package com.ufrn;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer implements Runnable {
    ActiveMQConnectionFactory connectionFactory = null;

    public TopicConsumer(ActiveMQConnectionFactory connectionFactory) {
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
                // Let's create a topic.
                Destination topicDestination = session.createTopic("CLIMATE");
                // Create a MessageProducer from the Session to the Topic or Queue
                MessageConsumer messageConsumer = session.createConsumer(topicDestination);
                // Get the message

                Message message = messageConsumer.receive();
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());

            } catch (JMSException jmse) {
                System.out.println("Exception: " + jmse.getMessage());
            }
        }
    }
}
