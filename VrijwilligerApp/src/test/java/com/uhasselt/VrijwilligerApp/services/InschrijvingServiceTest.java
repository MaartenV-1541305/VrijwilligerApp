package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IInschrijvingService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Benodigheid;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import com.uhasselt.VrijwilligerApp.models.Taak;
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

public class InschrijvingServiceTest {
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
    public void koppelInschrijvingMetAccountEnEvenementTest(){
        Inschrijving inschrijving = new Inschrijving();
        Mockito.when(repository.save(inschrijving)).thenReturn(inschrijving);

        Inschrijving result = inschrijvingService.koppelInschrijvingMetAccountEnEvenement(1, 1);

        Assertions.assertNotEquals(result.getId(), 0);
        Assertions.assertEquals(result.getAccount().getId(), 1);
        Assertions.assertEquals(result.getEvenement().getId(), 1);
    }

    @Test
    public void voegTaakToeTest(){
        Inschrijving inschrijving = new Inschrijving();
        Taak taak = new Taak();
        Mockito.when(repository.save(inschrijving)).thenReturn(inschrijving);

        Inschrijving result = inschrijvingService.voegTaakToe(taak, 1);

        Assertions.assertEquals(result.getTaak(), taak);
    }

    @Test
    public void verwijderTaakTest(){
        Inschrijving inschrijving = new Inschrijving();
        Mockito.when(repository.save(inschrijving)).thenReturn(inschrijving);

        Inschrijving result = inschrijvingService.verwijderTaak(1, 1);

        Assertions.assertNull(result.getTaak());
    }

    @Test
    public void voegBenodigheidToeTest(){
        Inschrijving inschrijving = new Inschrijving();
        Benodigheid benodigheid = new Benodigheid();
        Mockito.when(repository.save(inschrijving)).thenReturn(inschrijving);

        Inschrijving result = inschrijvingService.voegBenodigheidToe(benodigheid, 1);


        Assertions.assertTrue(result.getBenodigheden().contains(benodigheid));

    }

    @Test
    public void verwijderBenodigheidTest(){
        Inschrijving inschrijving = new Inschrijving();
        Benodigheid benodigheid = new Benodigheid();
        Mockito.when(repository.save(inschrijving)).thenReturn(inschrijving);

        Inschrijving result = inschrijvingService.verwijderBenodigheid(benodigheid.getId(), 1);

        Assertions.assertFalse(result.getBenodigheden().contains(benodigheid));
    }

    @Test
    public void schrijfInVoorEvenementTest(){
        Inschrijving inschrijving = new Inschrijving();
        Mockito.when(repository.save(inschrijving)).thenReturn(inschrijving);

        Inschrijving result = inschrijvingService.schrijfInVoorEvenement(1, 1);

        //Assertions.assertEquals(result);
    }

    @Test
    public void getInschrijving(int inschrijvingsId){
        Mockito.when(repository.getInschrijving(1)).thenReturn(null);
        Inschrijving result = inschrijvingService.getInschrijving(1);

        Assertions.assertEquals(result, null);
    }
    @Test
    public void deleteInschrijving(int inschrijvingsId){
        Mockito.when(repository.deleteInschrijving(1)).thenReturn(null);
        //Inschrijving result = inschrijvingService.deleteInschrijving(1);

        //Assertions.assertEquals(result, null);
    }



    @Test
    public void deleteInschrijvingWithId(){
        inschrijvingService.deleteInschrijving(fakeRandom.nextInt());
        Mockito.when(repository.findById(0L)).thenReturn(null);

        Inschrijving result = repository.findById(0L).get();

        Assert.assertNull(result);
    }
}



