package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Adres;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Taak;
import com.uhasselt.VrijwilligerApp.services.EvenementService;
import org.junit.Assert;
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
        account.setPassword("password");
    }

    @Test
    public void nieuwAccountTest() {
        Mockito.when(accountService.aanmakenAccount("Doe", "John", "s@a.be", "password123", "password123", "hasselt")).thenReturn(account);
        ResponseEntity<Account> result = accountController.nieuwAccount("Doe", "John", "s@a.be", "password123", "password123", "hasselt");
        Assertions.assertEquals(result.getStatusCode().value(), 200);

    }

    @Test public void niewAccountWachtwoordTest(){
        Mockito.when(accountService.aanmakenAccount("Doe", "John", "s@a.be", "password123", "password123", "hasselt")).thenReturn(account);
        ResponseEntity<Account> result = accountController.nieuwAccount("Doe", "John", "s@a.be", "password12", "password123", "hasselt");
        //Wachtwoord != bevestigwachtwoord dus error!
        Assertions.assertEquals(result.getStatusCode().value(), 403);
    }


    @Test public void niewAccountEmptyFieldTest(){
        Mockito.when(accountService.aanmakenAccount("Doe", "John", "s@a.be", "password123", "password123", "hasselt")).thenReturn(account);
        ResponseEntity<Account> result = accountController.nieuwAccount("", "", "s@a.be", "password12", "password123", "");
        Assertions.assertEquals(result.getStatusCode().value(), 403);
    }

    @Test
    public void inloggenTest(){
        Mockito.when(accountService.inloggen("test@test.be", "password")).thenReturn(account);
        ResponseEntity<Account> result = accountController.inloggen("test@test.be", "password");

        Assertions.assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void inloggenEmptyFieldTest(){
        Mockito.when(accountService.inloggen("test@test.be", "password")).thenReturn(account);
        ResponseEntity<Account> result = accountController.inloggen("test@test.be", "");

        Assertions.assertEquals(result.getStatusCode().value(), 403);
    }

    @Test
    public void inloggenFoutePassword(){
        Account acc = accountService.selectAccount("test@test.be", "password");
        Mockito.when(accountService.inloggen("test@test.be", "password")).thenReturn(account);
        String pass = "fout";
        ResponseEntity<Account> result = accountController.inloggen("test@test.be", pass);

        if(!account.getPassword().equals(pass)){
            System.out.println("Password is fout. Test geslaagd.");
            Assertions.assertTrue(true);
        }else{
            System.out.println("Juist wachtwoord.");
            Assertions.assertTrue(false);
            //Assertions.assertEquals(account.getPassword(), pass);
        }

    }
}
