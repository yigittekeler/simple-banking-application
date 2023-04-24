package com.eteration.simplebanking.services.impl;


import com.eteration.simplebanking.dto.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto getAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        AccountDto accountDto = new AccountDto();
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for(Transaction t : account.getTransactions()) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setAmount(t.getAmount());
            transactionDto.setDate(t.getDate());
            transactionDto.setType(t.getTransactionType());
            transactionDto.setApprovalCode(t.getApprovalCode());
            transactionDtoList.add(transactionDto);
        }
        accountDto.setAccountNumber(accountNumber);
        accountDto.setBalance(account.getBalance());
        accountDto.setCreateDate(account.getCreateDate());
        accountDto.setOwner(account.getOwner());
        accountDto.setTransactions(transactionDtoList);

        return accountDto;
    }

    @Override
    public TransactionStatus debit(String accountNumber, Double amount) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);

        account.post(new WithdrawalTransaction(amount,account));
        accountRepository.save(account);

        return TransactionStatus.builder()
                .status("OK")
                .approvalCode(account.getTransactions().get(account.getTransactions().size() - 1).getApprovalCode())
                .build();
    }

    @Override
    public TransactionStatus credit(String accountNumber, Double amount) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);

        account.post(new DepositTransaction(amount,account));
        accountRepository.save(account);

        return TransactionStatus.builder()
                .status("OK")
                .approvalCode(account.getTransactions().get(account.getTransactions().size() - 1).getApprovalCode())
                .build();

    }

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("no account with given accountNumber !"));
    }

}
