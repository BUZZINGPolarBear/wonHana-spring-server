package com.wonhana.kau.wonhana.sample;

//import  com.wonhana.kau.wonhana.sample.model.*;
import com.wonhana.kau.wonhana.config.BaseResponse;
import com.wonhana.kau.wonhana.dto.BankProductsDto;
import com.wonhana.kau.wonhana.dto.UserInfoDto;
import com.wonhana.kau.wonhana.repository.BankProductsRepository;
import com.wonhana.kau.wonhana.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SampleController {

    @Autowired
    private final SampleProvider sampleProvider;
    private final SampleService sampleService;

    private final BankProductsRepository bankProductsRepository;
    private final UserInfoRepository userInfoRepository;

    @GetMapping("/userInfoAll")
    public BaseResponse<UserInfoDto> showUserAll() {

        List<UserInfoDto> userInfoDto = userInfoRepository.showAll();
        BaseResponse baseResponse = new BaseResponse<>(userInfoDto);

        return baseResponse;
    }

    @GetMapping("/userInfoById/{userId}")
    public BaseResponse<UserInfoDto> showUserById(@PathVariable long userId) {
        List<UserInfoDto> userInfoDto = userInfoRepository.findById(userId);
        BaseResponse baseResponse = new BaseResponse<>(userInfoDto);

        return baseResponse;
    }



    @PostMapping("/sliderProduct")
    public BaseResponse<String> showSliderProducts(@RequestBody Integer slider) {

        BaseResponse baseResponse;

        if (slider <= 33){
            baseResponse = new BaseResponse<>("소극적인 상품 드림");
        }
        else if (slider <= 66){
            baseResponse = new BaseResponse<>("평범한 상품 드림");
        }
        else {
            baseResponse = new BaseResponse<>("대박 상품 드림");
        }

        return baseResponse;
    }


    // db의 유저들이 많이 갖고 있는 상품의 조회
    @PostMapping("/popularProduct")
    @ResponseBody
    public BaseResponse<String> showPopularProduct() {
        BaseResponse baseResponse = new BaseResponse<>("유저들이 가장 많이 선택한 상품 2개에요");

        return baseResponse;
    }
}
