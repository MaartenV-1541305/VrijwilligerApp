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

    private Account account;
    private Adres adres;

    @Before
    public void setUp() {
        accountService = Mockito.mock(IAccountService.class);
        adres = new Adres();
        adres.setGemeente("Hasselt");
        adres.setPostcode("3550"); // postcode int veranderen?

        accountController = new AccountController();
    }

    @Test
    public void nieuwAccountTest() {
        ResponseEntity<Account> account = accountController.nieuwAccount("Doe", "John", "s@a.be", "password123", "password123", adres);
        //TODO: Beter vergelijking vn verschillende mogelijkheden.
        int status = account.getStatusCode().value();
        Account result = account.getBody();
        //als passwords niet gelijk.
        //Assertions.assertEquals(status, 403);

        Assertions.assertEquals(status, 200);

        if (result != null) {
            Assertions.assertEquals(result.getVoornaam(), "John");
        }

    }
}
