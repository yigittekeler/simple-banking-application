package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="owner")
    private String owner;

    @Column(name="account_number",unique = true)
    private String accountNumber;

    @Column(name="balance")
    private Double balance;

    @Column(name="create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0d;
    }

    public Account() {

    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        if (transaction instanceof DepositTransaction) {
            this.balance += transaction.getAmount();
        } else if (transaction instanceof WithdrawalTransaction) {
            if(isSufficient(this.balance , transaction.getAmount())) {
                this.balance -= transaction.getAmount();
            }
        } else if (transaction instanceof PhoneBillPaymentTransaction) {
            PhoneBillPaymentTransaction phoneBillPaymentTransaction = (PhoneBillPaymentTransaction) transaction;
            if(isSufficient(this.balance , transaction.getAmount())) {
                this.balance -= phoneBillPaymentTransaction.getAmount();
            }
        }
        this.transactions.add(transaction);
    }

    private Boolean isSufficient(Double balance, Double amount) throws InsufficientBalanceException {
        if(balance - amount < 0) {
            throw new InsufficientBalanceException(HttpStatus.BAD_REQUEST,"You do not have sufficient funds to perform this transaction.");
        } else {
            return Boolean.TRUE;
        }
    }
}
