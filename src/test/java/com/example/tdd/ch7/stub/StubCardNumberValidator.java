package com.example.tdd.ch7.stub;

import com.example.tdd.ch7.CardNumberValidator;
import com.example.tdd.ch7.CardValidity;

// card Validator 대역
public class StubCardNumberValidator implements CardNumberValidator {
    private String invalidNo;
    private String theftNo;// 도난 카드

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }
    @Override
    public CardValidity validate(String cardNumber) {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID;
        }
        if (theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT;
        }
        return CardValidity.VALID;
    }
}
