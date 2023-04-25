package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.eteration.simplebanking.dto.TransactionStatus;
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


    @Test
    public void givenId_DebitWithMoreValueGetException_thenReturnJson()
    {

        Account account = new Account("yigit tekeler", "1234567890");

        Mockito.when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> {
            accountService.debit("1234567890", 1d);
        });

        Mockito.verify(accountRepository, Mockito.times(1)).findByAccountNumber("1234567890");
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
    throws Exception {

        Account account = new Account("yigit tekeler", "1234567890");

        Mockito.when(accountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        TransactionStatus ts1 = accountService.credit("1234567890", 200d);
        assertEquals(account.getBalance(),200d);
        TransactionStatus ts2 =accountService.debit("1234567890", 160d);

        assertEquals("OK", ts1.getStatus());
        assertEquals("OK", ts2.getStatus());
        assertEquals(40d, account.getBalance(),0.001);

        Mockito.verify(accountRepository, Mockito.times(2)).findByAccountNumber("1234567890");
        Mockito.verify(accountRepository, Mockito.times(2)).save(account);


    }

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

}
