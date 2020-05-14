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

import java.util.*;

//inschrijvingService
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

        repository = Mockito.mock(IInschrijvingRepository.class);
        inschrijvingService = new InschrijvingService(repository);

        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        account = new Account();
        account.setNaam("testuser");

        inschrijvingen = new ArrayList<>();
        Inschrijving i1 = new Inschrijving();
        i1.setAccount(account);
        Inschrijving i2 = new Inschrijving();
        i2.setAccount(account);

        inschrijvingen.add(i1);
        inschrijvingen.add(i2);

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

        Mockito.when(repository.getAllInschrijvingen(-5)).thenReturn(null);

        List<Inschrijving> result = inschrijvingService.getAllInschrijvingenByAccountId(-5);

        Assertions.assertEquals(result, null);
    }

    @Test
    public void deleteInschrijvingWithId(){
        inschrijvingService.deleteInschrijving(fakeRandom.nextInt());
        Mockito.when(repository.findById(0L)).thenReturn(null);

        Inschrijving result = repository.findById(0L).get();

        Assert.assertNull(result);
    }
}



