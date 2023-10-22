package com.banka.eureka;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cont {

    @GetMapping
    public String deneme(){
        return "deneme";
    }
}
