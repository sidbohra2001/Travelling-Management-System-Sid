package com.travel.bus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiscellaneousController {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String getHello(){
        return "Hello World";
    }

}
