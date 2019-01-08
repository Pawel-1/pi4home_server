package com.pi4home.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld
{
    @RequestMapping("/hello")
    public String initDb()
    {
        return "Hello World!";
    }

}
