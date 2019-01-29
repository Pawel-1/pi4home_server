package com.pi4home.server.services;

import com.pi4home.server.messagesBroker.QueueProducer;
import com.pi4home.server.model.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LightsService
{
    @Autowired
    Light entranceLight;

    @Autowired
    QueueProducer queueProducer;

    public Light switchLight() throws Exception
    {
        entranceLight.setTurnedOn(true);
        queueProducer.produce(entranceLight);
        return entranceLight;
    }

    public Light getLightStatus()
    {
        return entranceLight;
    }
}
