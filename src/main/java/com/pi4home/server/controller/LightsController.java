package com.pi4home.server.controller;

import com.pi4home.server.model.Light;
import com.pi4home.server.services.LightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LightsController
{
    @Autowired
    LightsService lightsService;

    @RequestMapping("/light/{name}")
    public List<Light> light(@PathVariable String name)
    {
        return lightsService.switchLight(name);
    }

    @RequestMapping("/lightStatus")
    public List<Light> lightStatus()
    {
        return lightsService.getLightStatus();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/light/")
    public Light light(@RequestBody Light light)
    {
        return lightsService.updateLightState(light);
    }
}
