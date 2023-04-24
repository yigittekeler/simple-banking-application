package com.eteration.simplebanking;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
//		Account account = new Account("yigit tekeler","1234");
//		DepositTransaction depositTransaction = new DepositTransaction();
//		depositTransaction.setAmount(100d);
//		depositTransaction.setAccount(account);
//		account.post(depositTransaction);
//
//		accountRepository.save(account);

//		Account account = accountRepository.findById(3L).get();
//		DepositTransaction depositTransaction = new DepositTransaction();
//		depositTransaction.setAmount(150d);
//		depositTransaction.setAccount(account);
//		account.post(depositTransaction);
//		accountRepository.save(account);

//		Account account = accountRepository.findById(3L).get();
//		PhoneBillPaymentTransaction trx = new PhoneBillPaymentTransaction("0544", "vodafone");
//
//		trx.setAmount(50d);
//		trx.setAccount(account);
//		account.post(trx);
//		accountRepository.save(account);
	}

}
