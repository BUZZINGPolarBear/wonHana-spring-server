package com.wonhana.kau.wonhana.frontProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/cost-manage/1")
    public String costManageLv1(){
        return "cost_manage_lv1";
    }

    @RequestMapping("/cost-manage/2")
    public String costManageLv2(){
        return "cost_manage_lv2";
    }

    @RequestMapping("/cost-manage/3")
    public String costManageLv3(){
        return "cost_manage_lv3";
    }

    @RequestMapping("/bankruptcy-prevent")
    public String bankruptcyPrevent(){
        return "bankruptcy-prevent";
    }


}
