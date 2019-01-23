package com.pi4home.server.controller;

import com.pi4home.server.QueueProducer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class HelloWorld
{
    @Autowired
    QueueProducer queueProducer;
    private final static String QUEUE_NAME = "hello";

    @RequestMapping("/hello")
    public String initDb() throws IOException, TimeoutException
    {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }

//        try
//        {
//            queueProducer.produce("hello from rabbit");
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
        return "Hello World!";
    }

}
