package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("phone_bill_payment")
@Getter
@Setter
public class PhoneBillPaymentTransaction extends Transaction{

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "provider")
    private String provider;

    public PhoneBillPaymentTransaction(String phoneNumber, String provider, Double amount, Account account) {
        super(amount,account);
        this.phoneNumber = phoneNumber;
        this.provider = provider;
    }
    public PhoneBillPaymentTransaction() {

    }


}
