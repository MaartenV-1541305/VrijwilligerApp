package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.repository.IAccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceTest {

    @Autowired
    private IAccountService service;
    private IAccountRepository repository;
    private Account account;

    @Before
    public void setUp(){
        repository = Mockito.mock(IAccountRepository.class);
        service = new AccountService(repository);

        account = new Account();
    }

    @Test
    public void selectAccountTest(){
        Mockito.when(repository.selectAccount("test@test.be", "TestWachtwoord")).thenReturn(account);
        Account result = service.selectAccount("test@test.be", "TestWachtwoord");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getEmail(), account.getEmail(), "email komen overeen!");
        Assertions.assertEquals(result.getPassword(), account.getPassword(), "pw komen overeen!");
    }


    @Test
    public void InsertAccountTest(){
        Mockito.when(repository.insertAccount("test@test.be", "TestWachtwoord", "TestWachtwoord", "naam","voornaam", "Hasselt" )).thenReturn(account);
        Account result = service.insertAccount("test@test.be", "TestWachtwoord", "TestWachtwoord", "naam","voornaam", "Hasselt");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getEmail(), account.getEmail(), "email komen overeen!");
        Assertions.assertEquals(result.getPassword(), account.getPassword(), "pw komen overeen!");
    }
}
