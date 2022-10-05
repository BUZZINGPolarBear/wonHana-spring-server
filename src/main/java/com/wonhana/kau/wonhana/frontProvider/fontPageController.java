package com.wonhana.kau.wonhana.frontProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class fontPageController {

    @RequestMapping("/")
    public String homePage(){
        return "assetStatus";
    }
    @RequestMapping("/cash-flow")
    public String cashFlow(){
        return "cash_flow";
    }

    @RequestMapping("/cost-manage")
    public String costManage(){
        return "cost_manage_lv4";
    }

}
