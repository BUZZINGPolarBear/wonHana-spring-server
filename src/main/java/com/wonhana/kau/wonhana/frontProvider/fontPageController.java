package com.wonhana.kau.wonhana.frontProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class fontPageController {

    @RequestMapping("/")
    public String homePage(){
        return "main_1";
    }

}
