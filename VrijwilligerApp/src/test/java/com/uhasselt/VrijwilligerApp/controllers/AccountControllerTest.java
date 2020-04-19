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
        //TODO: (note tomeself) constructor voorzien voor account.
        account = new Account();
        account.setVoornaam("John");
        account.setNaam("Doe");
        account.setEmail("s@s.be");
        account.setPassword("wachtwoord123");

        //TODO: zelfde hier.
        adres = new Adres();
        adres.setGemeente("Hasselt");
        adres.setPostcode("3550"); // postcode int veranderen?

        account.setAdres(adres);

        accountController = new AccountController();
    }

    @Test
    public void nieuwAccountTest() {
        int status = accountController.nieuwAccount(account).getStatusCode().value();
        Account result = accountController.nieuwAccount(account).getBody();
        //TODO voeg taak aan evenement toe, save of update nieuw evenement en check of taak toegevoegd is
        Assertions.assertEquals(status, 200);
        assert result != null;
        Assertions.assertEquals(result.getNaam(), "Doe");
    }
}
