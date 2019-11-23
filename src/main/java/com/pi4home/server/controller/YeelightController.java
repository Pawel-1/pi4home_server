package com.pi4home.server.controller;

import com.pi4home.server.model.Yeelight;
import com.pi4home.server.services.YeelightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YeelightController
{
    @Autowired
    YeelightService yeeslightService;
    private static final Logger logger = LoggerFactory.getLogger(YeelightController.class);


    @RequestMapping(method = RequestMethod.PUT, value = "/yeelight/")
    public List<Yeelight> yeelight(@RequestBody Yeelight yeelight)
    {
        logger.info("RQ: " + yeelight.getName() + " turned on: " + yeelight.isTurnedOn());
        yeeslightService.updateYeelightState(yeelight);
        return yeeslightService.getYeelightStatus();
    }
}
