package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;
//
//
//    @Test
//    public void givenId_Credit_thenReturnJson()
//    throws Exception {
//
//        Account account = new Account("Kerem Karaca", "17892");
//
//        doReturn(account).when(service).findAccount( "17892");
//        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new DepositTransaction(1000.0));
//        verify(service, times(1)).findAccount("17892");
//        assertEquals("OK", result.getBody().getStatus());
//    }
//
//    @Test
//    public void givenId_CreditAndThenDebit_thenReturnJson()
//    throws Exception {
//
//        Account account = new Account("Kerem Karaca", "17892");
//
//        doReturn(account).when(service).findAccount( "17892");
//        ResponseEntity<TransactionStatus> result = controller.credit( "17892", new DepositTransaction(1000.0));
//        ResponseEntity<TransactionStatus> result2 = controller.debit( "17892", new WithdrawalTransaction(50.0));
//        verify(service, times(2)).findAccount("17892");
//        assertEquals("OK", result.getBody().getStatus());
//        assertEquals("OK", result2.getBody().getStatus());
//        assertEquals(950.0, account.getBalance(),0.001);
//    }
//
    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson() {

        Account account = new Account("yigit tekeler", "1234567890");
        Mockito.when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> {
            accountService.credit("1234567890", 100d);
            assertEquals(account.getBalance(),100d);
            accountService.debit("1234567890", 200d);
        });
        Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber("1234567890");
        Mockito.verify(accountRepository, Mockito.times(1)).save(account);
    }

//
//    @Test
//    public void givenId_GetAccount_thenReturnJson()
//    throws Exception {
//
//        Account account = new Account("Kerem Karaca", "17892");
//
//        doReturn(account).when(service).findAccount( "17892");
//        ResponseEntity<Account> result = controller.getAccount(17892);
//        verify(service, times(1)).findAccount("17892");
//        assertEquals(account, result.getBody());
//    }

}
