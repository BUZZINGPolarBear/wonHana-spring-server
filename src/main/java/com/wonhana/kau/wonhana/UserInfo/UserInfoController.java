package com.wonhana.kau.wonhana.UserInfo;

//import  com.wonhana.kau.wonhana.UserInfo.model.*;
import com.wonhana.kau.wonhana.config.BaseResponse;
import com.wonhana.kau.wonhana.dto.*;
import com.wonhana.kau.wonhana.repository.BankProductsRepository;
import com.wonhana.kau.wonhana.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserInfoController {

    @Autowired
    private final UserInfoProvider userInfoProvider;
    private final UserInfoService userInfoService;

    private final BankProductsRepository bankProductsRepository;
    private final UserInfoRepository userInfoRepository;

    @GetMapping("/userInfoAll")
    public BaseResponse<ShowUserInfoDto> showUserAll() {

        List<ShowUserInfoDto> showUserInfoDto = userInfoRepository.showAll();
        BaseResponse baseResponse = new BaseResponse<>(showUserInfoDto);

        return baseResponse;
    }

    @GetMapping("/userInfoById/{userId}")
    public BaseResponse<ShowUserInfoDto> showUserById(@PathVariable long userId) {
        List<ShowUserInfoDto> showUserInfoDto = userInfoRepository.findById(userId);
        BaseResponse baseResponse = new BaseResponse<>(showUserInfoDto);

        return baseResponse;
    }

    @PostMapping("/addAsset")
    public BaseResponse addAsset(@RequestBody AssetDto assetDto) {
        userInfoRepository.saveAssetInfo(assetDto);

        return new BaseResponse<>("Asset에 삽입성공");
    }

    @PostMapping("/addDebt")
    public BaseResponse addDebt(@RequestBody DebtDto debtDto) {
        userInfoRepository.saveDebtInfo(debtDto);

        return new BaseResponse<>("Debt에 삽입성공");
    }

    @PostMapping("/addIncome")
    public BaseResponse addIncome(@RequestBody IncomeDto incomeDto) {
        userInfoRepository.saveIncomeInfo(incomeDto);

        return new BaseResponse<>("Income에 삽입성공");
    }

    @PostMapping("/addFlexibleExpenditure")
    public BaseResponse addFlexibleExpenditure(@RequestBody FlexibleExpenditureDto flexibleDto) {
        userInfoRepository.saveFlexibleExpenditure(flexibleDto);

        return new BaseResponse<>("FlexibleExpenditure에 삽입성공");
    }

    @PostMapping("/addFixedExpenditure")
    public BaseResponse addFixedExpenditure(@RequestBody FixedExpenditureDto fixedDto) {
        userInfoRepository.saveFixedExpenditure(fixedDto);

        return new BaseResponse<>("FixedExpenditure에 삽입성공");
    }
}
