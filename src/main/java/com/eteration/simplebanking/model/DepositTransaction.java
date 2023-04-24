package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("deposit")
@Entity
public class DepositTransaction extends Transaction {

    public DepositTransaction(Double amount, Account account) {
        super(amount,account);
    }
    public DepositTransaction() {
        super();
    }
}
