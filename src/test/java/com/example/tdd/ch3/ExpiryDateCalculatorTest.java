package com.example.tdd.ch3;


/*
 * 서비스를 사용하려면 매달1만원을 선불로 납부한다. 납부일 기준으로 한달 뒤가 서비스만료일이 된다,
 * 2개월이상 요금을 납부할 수 있다.
 * 10만원을 납부하면 서비스를 1년 제공한다.
 *
 * 납부한 금액 기준으로 서비스 만료일을 계산하는 기능
 * */


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    private void assertExpiryDate(PayData payData ,LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    public void 만원납부하면_한달뒤가_만료일이됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10000)
                        .build()
                ,LocalDate.of(2019, 4, 1));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10000)
                        .build()
                ,LocalDate.of(2019, 6, 5));
    }

    @Test
    public void 납부일과_한달_뒤_일자가_같지_않음(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10000)
                        .build()
                ,LocalDate.of(2019, 2, 28));
    }

    @Test
    @DisplayName("첫_납부일과_만료일의_일자가_다를때_만원_납부하면_첫납부일_기준으로_다음만료일정함")
    public void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData= PayData.builder()
                                .firstBillingDate(LocalDate.of(2019,1,31))
                                .billingDate(LocalDate.of(2019,2,28))
                                .payAmount(10000)
                                .build();


        assertExpiryDate(payData
                ,LocalDate.of(2019, 3, 31));



        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10000)
                .build();


        assertExpiryDate(payData2
                ,LocalDate.of(2019, 3, 30));
    }

    @Test
    public void 이만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20000)
                        .build()
                ,LocalDate.of(2019, 5, 1));
    }

    @Test
    public void 첫납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20000)
                        .build()
                ,LocalDate.of(2019, 4, 30));
    }

    @Test
    public void 십만원을_납부하면_1년_제공(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100000)
                        .build()
                ,LocalDate.of(2020, 1, 28));
    }


}
