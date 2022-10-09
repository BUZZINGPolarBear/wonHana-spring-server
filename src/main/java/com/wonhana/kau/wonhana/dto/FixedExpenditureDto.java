package com.wonhana.kau.wonhana.dto;

import lombok.Data;

@Data
public class FixedExpenditureDto {

    private Integer userId;
    private Integer houseLoanInterest;
    private Integer carLent;
    private Integer carInsurance;
    private Integer dues;
    private Integer communicationCost;
    private Integer subscribeFee;
}
