package com.pi4home.server.services;

import com.pi4home.server.controller.YeelightController;
import com.pi4home.server.messagesBroker.YeelightsQueueProducer;
import com.pi4home.server.model.Yeelight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YeelightService
{

    @Autowired
    YeelightsQueueProducer yeelightsQueueProducer;

    public List<Yeelight> switchYeelight(String name)
    {
        return null;
    }

    public List<Yeelight> getYeelightStatus()
    {
        return null;
    }

    public void updateYeelightState(Yeelight yeelight)
    {
        try
        {
            yeelightsQueueProducer.produce(yeelight);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
