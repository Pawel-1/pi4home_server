package com.pi4home.server.controller;

import com.pi4home.server.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld
{
    @Autowired
    QueueProducer queueProducer;

    @RequestMapping("/hello")
    public String initDb()
    {
        try
        {
            queueProducer.produce("hello from rabbit");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}
