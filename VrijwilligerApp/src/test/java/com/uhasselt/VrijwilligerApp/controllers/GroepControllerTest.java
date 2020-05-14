/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Adres;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import com.uhasselt.VrijwilligerApp.repository.IGroepRepository;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author vandenboer
 */
public class GroepControllerTest {
    
    @Autowired
    private GroepController groepController;
    
    @Autowired
    private IGroepService groepService;
    private IGroepRepository repository;
    private Random fakeRandom;
    private Account eigenaar;
    private Account lid;
    private GroepsLid groepsLid;
    private Groep groep;
    
    @Before
    public void setup() {
        this.repository = Mockito.mock(IGroepRepository.class);
        this.groepService = Mockito.mock(IGroepService.class);
        
        this.eigenaar = new Account();
        this.eigenaar.setVoornaam("User");
        this.eigenaar.setNaam("GroepTest");
        this.eigenaar.setEmail("user@groep.test");
        this.eigenaar.setPassword("GroepTest");
        
        Adres adres = new Adres();
        adres.setGemeente("Hasselt");
        adres.setPostcode("3500");
        this.eigenaar.setAdres(adres);
        
        this.lid = new Account();
        this.lid.setVoornaam("Lid");
        this.lid.setNaam("GroepTest");
        this.lid.setEmail("lid@groep.test");
        this.lid.setPassword("GroepTest");
        this.lid.setAdres(adres);
        
        this.groepsLid = new GroepsLid();
        this.groepsLid.setAccount(lid);
        this.groepsLid.setGroep(groep);
        this.groepsLid.setAdmin(false);
        
        this.groep.getLeden().add(groepsLid);
        
        this.groepController = new GroepController(this.groepService);
    }
    
    @Test
    @SuppressWarnings("null")
    public void groepAanmakenTest() {
        Mockito.when(repository.save(this.groep)).thenReturn(this.groep);
        
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ResponseEntity<Groep> groep = this.groepController.nieuwGroep("Groep 1", this.eigenaar);
        
        Groep result = groep.getBody();
        int status = groep.getStatusCodeValue();
        
        Assertions.assertEquals(200, status);
        Assertions.assertEquals("Groep 1", result.getNaam());
        Assertions.assertEquals(this.eigenaar, result.getMaker().getAccount());
        
        this.groep = result;
    }
    
    @Test
    @SuppressWarnings("null")
    public void zetBeschrijvingTest() {
        Mockito.when(repository.updateBeschrijving(this.groep.getBeschrijving(), this.groep.getId())).thenReturn(this.groep);
        
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ResponseEntity<Groep> groep = this.groepController.zetBeschrijving(this.groep, "Dit is een test omschrijving");
        
        Groep result = groep.getBody();
        int status = groep.getStatusCodeValue();
        
        Assertions.assertEquals(200, status);
        Assertions.assertEquals("Dit is een test omschrijving", result.getBeschrijving());
    }
    
    @Test
    @SuppressWarnings("null")
    public void voegGroepsLidToeTest() {
        Mockito.when(repository.voegGroepslidToe(this.lid, false, this.groep.getId())).thenReturn(groep);
        
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ResponseEntity<Groep> groep = this.groepController.voegGroepsLidToe(this.groep, this.lid);
        
        Groep result = groep.getBody();
        int status = groep.getStatusCodeValue();
        
        Assertions.assertEquals(200, status);
        boolean isLid = false;
        
        for (GroepsLid groepsLid : result.getLeden()) {
            if (groepsLid.getAccount().equals(this.lid)) {
                isLid = true;
            }
        }
        
        Assertions.assertTrue(isLid);
    }
    
    
}
