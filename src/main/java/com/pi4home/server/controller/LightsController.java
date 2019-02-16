package com.pi4home.server.controller;

import com.pi4home.server.model.Light;
import com.pi4home.server.services.LightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LightsController
{
    @Autowired
    LightsService lightsService;

    @RequestMapping("/light/{name}")
    public List<Light> light(@PathVariable String name) throws Exception
    {
        return lightsService.switchLight(name);
    }

    @RequestMapping("/lightStatus")
    public List<Light> lightStatus()
    {
        return lightsService.getLightStatus();
    }
}
