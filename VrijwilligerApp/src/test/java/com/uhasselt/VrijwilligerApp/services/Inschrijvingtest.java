package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IInschrijvingService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import com.uhasselt.VrijwilligerApp.repository.IInschrijvingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Inschrijvingtest {
    @Autowired
    private IInschrijvingService inschrijvingService;

    @Autowired
    private IInschrijvingRepository repository;
    private Random fakeRandom;
    private List<Inschrijving> inschrijvingen;
    private Account account;

    @Before
    public void setUp(){
        inschrijvingService = new InschrijvingService();
        repository = Mockito.mock(repository.getClass());

        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        account = new Account();
        account.setNaam("testuser");

        inschrijvingen = new ArrayList<>();
        Inschrijving i1 = new Inschrijving();
        i1.setAccount(account);
        Inschrijving i2 = new Inschrijving();
        i2.setAccount(account);

    }

    @Test
    public void getInschrijvingenByAccountTest(){
        Mockito.when(repository.getAllInschrijvingen(fakeRandom.nextInt())).thenReturn(inschrijvingen);
        List<Inschrijving> result = inschrijvingService.getAllInschrijvingenByAccountId(fakeRandom.nextInt());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result , inschrijvingen);
        Assertions.assertEquals(result.size(), inschrijvingen.size());
        Assertions.assertEquals(result.get(0).getAccount().getNaam(), "testuser" );
        Assertions.assertEquals(result.get(1).getAccount().getNaam(), "testuser");

    }
    @Test
    public void getInschrijvingenWithInvalidAccountId(){

        Mockito.when(repository.getAllInschrijvingen(-58494468)).thenReturn(null);

        List<Inschrijving> result = inschrijvingService.getAllInschrijvingenByAccountId(fakeRandom.nextInt());

        Assertions.assertEquals(result, null);
    }
}



