package com.pi4home.server.controller;

import com.pi4home.server.model.Blind;
import com.pi4home.server.services.BlindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BlindsController
{
    @Autowired
    BlindsService blindsService;

    @RequestMapping(method = RequestMethod.PUT, value = "/blind/")
    public Blind blind(@RequestBody Blind blind)
    {
        return blindsService.setBlindState(blind);
    }
}
