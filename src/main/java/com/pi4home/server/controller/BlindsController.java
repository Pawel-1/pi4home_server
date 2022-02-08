package com.pi4home.server.controller;

import com.pi4home.server.jpa.BlindRepository;
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
    private BlindRepository blindRepository;

    @Autowired
    BlindsService blindsService;

    @RequestMapping(method = RequestMethod.PUT, value = "/blind/")
    public Blind blind(@RequestBody Blind blind)
    {
        logger.info("Received RQ for updating Blind: " + blind.getName());
        return blindsService.setBlindState(blind);
    }

    @RequestMapping("/blindStatus")
    public Iterable<Blind> BlindStatus()
    {
        return blindRepository.findAll();
    }

    @RequestMapping("/blinds/{name}/{percentageMaskingState}")
    public void updateBlindStateByValue(@PathVariable String name, @PathVariable Double percentageMaskingState)
    {
        blindsService.updateBlindStateByValue(name, percentageMaskingState);
    }

    @RequestMapping("/blindsDB/{name}/{percentageMaskingState}")
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
