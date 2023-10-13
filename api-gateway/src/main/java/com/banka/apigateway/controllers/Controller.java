package com.banka.apigateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("deneme")
    public String deneme(){
        return "deneme";
    }
}
