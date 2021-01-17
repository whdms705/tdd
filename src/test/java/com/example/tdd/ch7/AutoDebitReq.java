package com.example.tdd.ch7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class AutoDebitReq {
    private String userId;
    private String cardNumber;
}
