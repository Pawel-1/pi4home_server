package com.pi4home.server.services;

import com.pi4home.server.messagesBroker.QueueProducer;
import com.pi4home.server.model.Blind;
import org.springframework.beans.factory.annotation.Autowired;

public class BlindsService
{
    @Autowired
    QueueProducer queueProducer;

    public Blind setBlindState(Blind blind)
    {
        try
        {
            queueProducer.produce(blind);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return blind;
    }
}
