package com.eteration.simplebanking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountDto {

    private String owner;

    private String accountNumber;

    private Double balance;

    private LocalDateTime createDate = LocalDateTime.now();

    private List<TransactionDto> transactions = new ArrayList<>();
}
