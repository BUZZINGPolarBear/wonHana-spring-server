package com.wonhana.kau.wonhana;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHello {

    @GetMapping("/")
    public String helloHANA() {

        return "hi!";
    }
}
