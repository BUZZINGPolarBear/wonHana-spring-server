package com.wonhana.kau.wonhana.sample;

//import  com.wonhana.kau.wonhana.sample.model.*;
import com.wonhana.kau.wonhana.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SampleController {

    @Autowired
    private final SampleProvider sampleProvider;
    private final SampleService sampleService;

    @ResponseBody
    @GetMapping("/test")
    public BaseResponse<String> testHelloWorld(){
        return new BaseResponse<>("Hello world!");
    }

}
