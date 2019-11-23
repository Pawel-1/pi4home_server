package com.pi4home.server.controller;

import com.pi4home.server.model.Yeelight;
import com.pi4home.server.services.YeelightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class YeelightController
{
    @Autowired
    YeelightService yeeslightService;

    @RequestMapping("/yeelight/{name}")
    public List<Yeelight> light(@PathVariable String name)
    {
        return yeeslightService.switchYeelight(name);
    }

    @RequestMapping("/yeelightStatus")
    public List<Yeelight> lightStatus()
    {
        return yeeslightService.getYeelightStatus();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/yeelight/")
    public List<Yeelight> light(@RequestBody Yeelight yeelight)
    {
        System.out.println("RQ: " + yeelight.getName() + " turned on: " + yeelight.isTurnedOn());
        yeeslightService.updateYeelightState(yeelight);
        return yeeslightService.getYeelightStatus();
    }
}
