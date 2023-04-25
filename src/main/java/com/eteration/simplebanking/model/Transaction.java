package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date = LocalDateTime.now();

    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "account_id", referencedColumnName = "id")
    @JsonBackReference
    private Account account;

    @Column(name = "approval_code")
    private UUID approvalCode;

    @Column(name = "transaction_type", insertable = false, updatable = false)
    private String transactionType;

    public Transaction(Double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }


    @PrePersist
    protected void prePersist() {
        this.approvalCode = UUID.randomUUID();
    }
}
