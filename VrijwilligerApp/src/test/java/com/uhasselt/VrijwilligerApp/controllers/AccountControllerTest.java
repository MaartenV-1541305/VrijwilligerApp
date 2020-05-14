package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Adres;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Taak;
import com.uhasselt.VrijwilligerApp.services.EvenementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Autowired
    private IAccountService accountService;
    private Adres adres;
    private Account account;

    @Before
    public void setUp() {
        accountService = Mockito.mock(IAccountService.class);
        adres = new Adres();
        adres.setGemeente("Hasselt");
        adres.setPostcode("3550");

        accountController = new AccountController(accountService);
        account = new Account();
    }

    @Test
    public void nieuwAccountTest() {
        Mockito.when(accountService.aanmakenAccount("Doe", "John", "s@a.be", "password123", "password123", "hasselt")).thenReturn(account);
        ResponseEntity<Account> result = accountController.nieuwAccount("Doe", "John", "s@a.be", "password123", "password123", "hasselt");
        Assertions.assertEquals(result.getStatusCode().value(), 200);
        /*
        ResponseEntity<Account> account = accountController.nieuwAccount("Doe", "John", "s@a.be", "password123", "password123", adres);
        int status = account.getStatusCode().value();
        Account result = account.getBody();

        Assertions.assertEquals(status, 200);
        if (result != null) {
            Assertions.assertEquals(result.getVoornaam(), "John");
        }
        */
    }

    @Test
    public void inloggenTest(){
        Mockito.when(accountService.inloggen("test@test.be", "password")).thenReturn(account);
        ResponseEntity<Account> result = accountController.inloggen("test@test.be", "password");

        Assertions.assertEquals(result.getStatusCode().value(), 200);
        //kan eventueel uitbreiden en meerde var test.
    }


}
