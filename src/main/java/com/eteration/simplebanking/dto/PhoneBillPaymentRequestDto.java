package com.eteration.simplebanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneBillPaymentRequestDto {

    private Double amount;
    private String phoneNumber;
    private String provider;
}
