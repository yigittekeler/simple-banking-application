package com.eteration.simplebanking;



import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
	
	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertEquals("Kerem Karaca", account.getOwner());
		assertEquals("17892", account.getAccountNumber());
		assertEquals(0, (double) account.getBalance());
	}

	@Test
	public void testDepositIntoBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.post(new DepositTransaction(100d,account));
		assertEquals(100, (double) account.getBalance());

	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {

		Account account = new Account("Demet Demircan", "9834");

		account.post( new DepositTransaction(100d,account));
        assertEquals(100, (double) account.getBalance());

		account.post(new WithdrawalTransaction(65d,account));
		assertEquals(35,(double) account.getBalance());
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");

			account.post( new DepositTransaction(100d,account));
			account.post(new WithdrawalTransaction(150d,account));
		  });

	}

	@Test
	public void testTransactions() throws InsufficientBalanceException {

		Account account = new Account("Canan Kaya", "1234");
		assertEquals(0, account.getTransactions().size());

		DepositTransaction depositTrx = new DepositTransaction();
		depositTrx.setAccount(account);
		depositTrx.setAmount(100d);
		assertNotNull(depositTrx.getDate());
		account.post(depositTrx);
		assertEquals(100, (double) account.getBalance());
		assertEquals(1, account.getTransactions().size());

		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction();
		withdrawalTrx.setAccount(account);
		withdrawalTrx.setAmount(60d);
		assertNotNull(withdrawalTrx.getDate());
		account.post(withdrawalTrx);
		assertEquals(40, (double) account.getBalance());
		assertEquals(2, account.getTransactions().size());

		account.post(new PhoneBillPaymentTransaction("05426898745","Turkcell",15d,account));
		assertEquals(25, (double) account.getBalance());
	}
}
