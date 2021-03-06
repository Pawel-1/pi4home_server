package com.pi4home.server.services;

import com.pi4home.server.messagesBroker.LightsQueueProducer;
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
    LightsQueueProducer lightsQueueProducer;

    public List<Light> switchLight(String name)
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

        produceToQueue(lightByName);

        return lightList;
    }


    public List<Light> getLightStatus()
    {
        return lightList;
    }

    public void updateLightState(Light lightFromRq)
    {
        Light lightByName = getLightByName(lightFromRq.getName());
        lightByName.setTurnedOn(lightFromRq.isTurnedOn());

        produceToQueue(lightByName);
    }

    private Light getLightByName(String name)
    {
        return lightList
                .stream()
                .filter(light -> light.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }


    private void produceToQueue(Light lightByName)
    {
        try
        {
            lightsQueueProducer.produce(lightByName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
