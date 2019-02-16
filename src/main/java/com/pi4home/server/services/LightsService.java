package com.pi4home.server.services;

import com.pi4home.server.messagesBroker.QueueProducer;
import com.pi4home.server.model.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LightsService
{
    @Autowired
    private Light entranceLight;

    @Autowired
    private Light sidewalkLight;

    @Autowired
    List<Light> lightList = Arrays.asList(entranceLight, sidewalkLight);

    @Autowired
    QueueProducer queueProducer;

    public List<Light> switchLight(String name) throws Exception
    {
        Light lightByName = getLightByName(name);

        if (lightByName.isTurnedOn())
        {
            lightByName.setTurnedOn(false);
        }
        else if (!lightByName.isTurnedOn())
        {
            lightByName.setTurnedOn(true);
        }

        queueProducer.produce(lightByName);

        return lightList;
    }

    private Light getLightByName(String name)
    {
        return lightList
                .stream()
                .filter(light -> light.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public List<Light> getLightStatus()
    {
        return lightList;
    }
}
