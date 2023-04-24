package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;

public interface AccountService {

    AccountDto getAccount(String accountNumber);

    TransactionStatus debit(String accountNumber, Double amount) throws InsufficientBalanceException;

    TransactionStatus credit(String accountNumber, Double amount) throws InsufficientBalanceException;
}
