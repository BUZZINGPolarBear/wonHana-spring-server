package com.wonhana.kau.wonhana.dto;

import lombok.Data;

@Data
public class UserInfoDto {

    private Long id;

//  고정지출
    private Integer houseLoanInterest;
    private Integer carLent;
    private Integer carInsurance;
    private Integer dues;
    private Integer communicationCost;
    private Integer subscribeFee;

//  유동지출
    private Integer coffee;
    private Integer food;
    private Integer snack;
    private Integer liquidNCigarette;
    private Integer necessityProduct;
    private Integer oil;

//  자산
    private Integer car;
    private Integer accountBalance;
    private Integer stock;
    private Integer house;

//  부채
    private Integer houseLoan;
    private Integer carLoan;

//  총합
    private Integer asset;
    private Integer liabilities;
    private Integer outcome;

}
