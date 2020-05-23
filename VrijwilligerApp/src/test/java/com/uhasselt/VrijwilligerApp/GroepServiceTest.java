/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp;

import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import com.uhasselt.VrijwilligerApp.repository.IGroepRepository;
import com.uhasselt.VrijwilligerApp.services.GroepService;
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
    private IGroepService groepService;

    @Autowired
    private IGroepRepository repository;
    private Random fakeRandom;
    private List<Groep> groepen;
    
    @Before
    public void setUp(){

        repository = Mockito.mock(IGroepRepository.class);
        groepService = new GroepService(repository);
        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        Account a1 = new Account();
        Account a2 = new Account();
        
        GroepsLid gl1 = new GroepsLid();
        gl1.setAccount(a1);
        GroepsLid gl2 = new GroepsLid();
        gl2.setAccount(a2);
        
        groepen = new ArrayList<>();
        Groep g1 = new Groep();
        g1.setNaam("Groep 1");
        g1.setMaker(gl1);
        Groep g2 = new Groep();
        g2.setNaam("Groep 2");
        g2.setMaker(gl2);
        
        this.groepen.add(g1);
        this.groepen.add(g2);
        
    }
    
    @Test
    public void selectGroepenTest() {
        Mockito.when(repository.getGroepenPerAccount(fakeRandom.nextInt())).thenReturn(groepen);

        List<Groep> result = groepService.getAllGroepenOfAccount(fakeRandom.nextInt());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result , groepen);
        Assertions.assertEquals(result.size(), groepen.size());
        Assertions.assertEquals("Groep 1", result.get(0).getNaam());
        Assertions.assertEquals("Groep 2", result.get(1).getNaam());
    }
    
}
