package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.PhoneBillPaymentRequestDto;
import com.eteration.simplebanking.dto.TransactionStatus;
import com.eteration.simplebanking.dto.TrxRequestDto;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @PostMapping(value = "/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@RequestBody TrxRequestDto request, @PathVariable String accountNumber) throws InsufficientBalanceException {
        return ResponseEntity.ok(accountService.credit(accountNumber,request.getAmount()));
    }
    @PostMapping(value = "/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@RequestBody TrxRequestDto request, @PathVariable String accountNumber) throws InsufficientBalanceException {
        return  ResponseEntity.ok(accountService.debit(accountNumber,request.getAmount()));
	}

    @PostMapping(value = "/phoneBillPayment/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBillPayment(@RequestBody PhoneBillPaymentRequestDto request, @PathVariable String accountNumber) throws InsufficientBalanceException {
        return  ResponseEntity.ok(accountService.phoneBillPayment(accountNumber,request.getAmount(), request.getPhoneNumber(),request.getProvider()));
    }
}