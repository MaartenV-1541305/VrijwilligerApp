package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IInschrijvingService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InschrijvingControllerTest {
    @Autowired
    private InschrijvingController controller;

    @Autowired
    private IInschrijvingService inschrijvingService;
    private List<Inschrijving> inschrijvingen;
    private Account account;
    private Random fakeRandom;

    @Before
    public void setUp(){
        account = new Account();

        Inschrijving inschr1 = new Inschrijving();
        inschr1.setAccount(account);
        Inschrijving inschr2 = new Inschrijving();
        inschr2.setAccount(account);

        inschrijvingen = new ArrayList<>();
        inschrijvingen.add(inschr1);
        inschrijvingen.add(inschr2);

        inschrijvingService= Mockito.mock(IInschrijvingService.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

    }

    //taak wss rood omdat controller null terug geeft.
    @Test
    public void getInschrijvingByAccountIdTest() throws Exception {
        Mockito.when(inschrijvingService.getAllInschrijvingenByAccountId(fakeRandom.nextInt())).thenReturn(inschrijvingen);
        List<Inschrijving> result = controller.getInschrijvingenByAccount(fakeRandom.nextInt()).getBody();

        Assertions.assertEquals(result , inschrijvingen);
        Assertions.assertEquals(account.getId(), inschrijvingen.get(0).getAccount().getId());
        Assertions.assertEquals(account.getId(), inschrijvingen.get(1).getAccount().getId());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), inschrijvingen.size());


    }

    @Test
    public void getInschrijvingenByInvalidAccountIdTest(){
        Mockito.when(inschrijvingService.getAllInschrijvingenByAccountId(-582)).thenReturn(null);
        List<Inschrijving> result = controller.getInschrijvingenByAccount(-582).getBody();

        Assertions.assertEquals(result, null);


    }
}
