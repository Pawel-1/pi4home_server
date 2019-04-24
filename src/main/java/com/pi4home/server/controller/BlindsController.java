package com.pi4home.server.controller;

import com.pi4home.server.model.Blind;
import com.pi4home.server.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlindsController
{
    @Autowired
    BlindsService blindsService;

    @RequestMapping(method = RequestMethod.PUT, value = "/blind/")
    public Blind blind(@RequestBody Blind blind)
    {
        return blindsService.setBlindState(blind);
    }

    @RequestMapping("/blindStatus")
    public List<Blind> BlindStatus()
    {
        return blindsService.getBlindStatus();
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
