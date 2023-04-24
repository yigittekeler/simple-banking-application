package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("withdrawal")
@Entity
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(Double amount, Account account) {
        super(amount,account);
    }
    public WithdrawalTransaction() {
        super();
    }
}


