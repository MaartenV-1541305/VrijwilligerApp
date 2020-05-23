package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IInschrijvingService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import org.junit.Assert;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

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
        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

    }

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

    @Test
    public void deleteInschrijvingenByInschrijvingId(){
        Mockito.when(inschrijvingService.deleteInschrijving(fakeRandom.nextInt())).thenReturn(true);
        int statusCode = controller.deleteInschrijving(fakeRandom.nextInt()).getStatusCode().value();

        Mockito.verify(inschrijvingService).deleteInschrijving(1);
        Assertions.assertEquals(statusCode, 200);
    }
    @Test
    public void getInschrijving(){
        Mockito.when(inschrijvingService.getInschrijving(1)).thenReturn(null);
        Inschrijving result = controller.getInschrijving(1).getBody();

        Assertions.assertEquals(result, null);
    }

    @Test
    public void isAanwezigTest(){
        Mockito.when(inschrijvingService.putAanwezigheid(1,true)).thenReturn(null);
        int statusCode = controller.isAanwezig(1,true).getStatusCode().value();

        Mockito.verify(inschrijvingService).putAanwezigheid(1,true);
        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void getAanwezighedenTest(){
        Mockito.when(inschrijvingService.getAanwezigheden(account.getId())).thenReturn(inschrijvingen);
        ResponseEntity<List<Inschrijving>> response = controller.getAanwezigheden(account.getId());
        List<Inschrijving> result = response.getBody();
        int statusCode = response.getStatusCode().value();


        Assertions.assertEquals(result , inschrijvingen);
        Assertions.assertEquals(account.getId(), inschrijvingen.get(0).getAccount().getId());
        Assertions.assertEquals(account.getId(), inschrijvingen.get(1).getAccount().getId());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), inschrijvingen.size());
        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void BerekenNieuweScoreTest(){
        Mockito.when(inschrijvingService.getAanwezigheden(account.getId())).thenReturn(inschrijvingen);
        ResponseEntity<List<Inschrijving>> response = controller.getAanwezigheden(account.getId());
        List<Inschrijving> aanwezigheden = response.getBody();
        int statusCode = response.getStatusCode().value();

        double score = controller.berekenNieuweScore(aanwezigheden);

        Mockito.when(inschrijvingService.putScore(account.getId(),score)).thenReturn(true);
        Mockito.verify(inschrijvingService).putScore(account.getId(),score);

        Assertions.assertEquals(score, 0);
    }



    // @Test
    // public void deleteInschrijving(int inschrijvingsId){
    //     Mockito.when(inschrijvingService.deleteInschrijving(1)).thenReturn(null);
    //     Inschrijving result = controller.deleteInschrijving(1).getBody();

    //     Assertions.assertEquals(result, null);
    // }

}
