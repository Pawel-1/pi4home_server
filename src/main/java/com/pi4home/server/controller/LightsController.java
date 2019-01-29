package com.pi4home.server.controller;

import com.pi4home.server.model.Light;
import com.pi4home.server.services.LightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightsController
{
    @Autowired
    LightsService lightsService;

    @RequestMapping("/light")
    public Light light() throws Exception
    {
        return lightsService.switchLight();
    }

    @RequestMapping("/lightStatus")
    public Light lightStatus()
    {
        return lightsService.getLightStatus();
    }
}
