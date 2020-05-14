/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.controllers.GroepController;
import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Adres;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import com.uhasselt.VrijwilligerApp.repository.IGroepRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vandenboer
 */
public class GroepServiceTest {
    
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
    private Groep groepNieuw;
    private List<Groep> gevondenGroepen;
    
    @Before
    public void setUp(){

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
        
        this.groep = new Groep();
        this.groep.setNaam("groep1");
        this.groep.setBeschrijving("Besschrijving groep1");
        this.groep.setMaker(groepsLid);
        this.groep.getLeden().add(groepsLid);
        this.groep.setId(Math.abs(fakeRandom.nextLong()));
        
        this.groepController = new GroepController(this.groepService);
        
        this.gevondenGroepen = new ArrayList<>();
        this.gevondenGroepen.add(groep);

    }
    
    @Test
    public void getGroepOpNaamTest(){
        Mockito.when(this.repository.findByName(this.groep.getNaam())).thenReturn(this.gevondenGroepen);

        List<Groep> result = this.repository.findByName(this.groep.getNaam());

        Assertions.assertEquals(result, this.gevondenGroepen);
    }

    @Test
    public void insertGroepTest() {
        Mockito.when(this.repository.save(this.groep)).thenReturn(this.groep);
        
        Groep result = this.repository.save(this.groep);
        
        Assertions.assertEquals(result.getId(), this.groep.getId());
        Assertions.assertEquals(result.getBeschrijving(), this.groep.getBeschrijving());
        Assertions.assertEquals(result.getNaam(), this.groep.getNaam());
    }
    
    @Test
    public void updateGroepTest() {
        String naam = "groep1update";
        String beschrijving = "nieuwe beschrijving";
        Groep groep2 = new Groep();
        groep2.setBeschrijving(beschrijving);
        groep2.setNaam(naam);
        groep2.setId(this.groep.getId());
        groep2.setAdmins(this.groep.getAdmins());
        groep2.setLeden(this.groep.getLeden());
        groep2.setVerified(this.groep.isVerified());
        
        Mockito.when(this.repository.save(this.groep)).thenReturn(this.groep);
        Mockito.when(this.repository.updateGroep(naam, beschrijving, this.groep.getId())).thenReturn(groep2);
        
        Groep groepOud = this.repository.save(this.groep);
        Groep groepNieuwer = this.repository.updateGroep(naam, beschrijving, this.groep.getId());
        
        Assertions.assertEquals(groepOud.getId(), groepNieuwer.getId());
        Assertions.assertEquals(this.groep.getId(), groepNieuwer.getId());
        Assertions.assertEquals(groepNieuwer.getNaam(), groep2.getNaam());
        Assertions.assertEquals(groepNieuwer.getBeschrijving(), groep2.getBeschrijving());
        Assertions.assertNotEquals(groepOud.getNaam(), groepNieuwer.getNaam());
        Assertions.assertNotEquals(groepOud.getBeschrijving(), groepNieuwer.getBeschrijving());
    }
    
}
