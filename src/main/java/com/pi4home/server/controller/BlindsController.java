package com.pi4home.server.controller;

import com.pi4home.server.model.Blind;
import com.pi4home.server.services.BlindsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlindsController
{
    private static final Logger logger = LoggerFactory.getLogger(BlindsController.class);


    @Autowired
    BlindsService blindsService;

    @RequestMapping(method = RequestMethod.PUT, value = "/blind/")
    public Blind blind(@RequestBody Blind blind)
    {
        logger.info("Received RQ for updating Blind: " + blind.getName());
        return blindsService.setBlindState(blind);
    }

    @RequestMapping("/blindStatus")
    public List<Blind> BlindStatus()
    {
        return blindsService.getBlindStatus();
    }

    @RequestMapping("/blinds/{name}/{percentageMaskingState}")
    public void updateBlindStateByValue(@PathVariable String name, @PathVariable Double percentageMaskingState)
    {
        blindsService.updateBlindStateByValue(name, percentageMaskingState);
    }

    @RequestMapping("/blinds/{name}/{percentageMaskingState}")
    public List<Blind> updateBlindStateInDbOnly(@PathVariable String name, @PathVariable Double percentageMaskingState)
    {
        return blindsService.updateBlindStateInDb(name, percentageMaskingState);
    }

    @RequestMapping("/initDb")
    public void initDb()
    {
        blindsService.initDb();
    }
}
