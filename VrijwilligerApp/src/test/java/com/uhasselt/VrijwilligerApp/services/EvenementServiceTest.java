package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.repository.EvenementRepository;
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


public class EvenementServiceTest {

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private EvenementRepository repository;
    private Random fakeRandom;
    private List<Evenement> georganiseerdeEvenementen;

    @Before
    public void setUp(){
        evenementService = new EvenementService();
        repository = Mockito.mock(repository.getClass());

        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        georganiseerdeEvenementen = new ArrayList<Evenement>();
        Evenement e1 = new Evenement();
        e1.setNaam("evenement1");
        Evenement e2 = new Evenement();
        e2.setNaam("evenement2");
        georganiseerdeEvenementen.add(e1);
        georganiseerdeEvenementen.add(e2);

    }

    @Test
    public void selectGeorganiseerdeEvenementenTest(){
        Mockito.when(repository.getGeorganiseerdeEvenementen(fakeRandom.nextInt())).thenReturn(georganiseerdeEvenementen);

        List<Evenement> result = evenementService.getAllGeorganiseerdeEvenementen(fakeRandom.nextInt());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result , georganiseerdeEvenementen);
        Assertions.assertEquals(result.size(), georganiseerdeEvenementen.size());
        Assertions.assertEquals(result.get(0).getNaam(), "evenement1");
        Assertions.assertEquals(result.get(1).getNaam(), "evenement2");

    }

    @Test
    public void selectGeorganiseerdeEvenementenWithInvalidAccountId(){

        Mockito.when(repository.getGeorganiseerdeEvenementen(-58494468)).thenReturn(null);

        List<Evenement> result = evenementService.getAllGeorganiseerdeEvenementen(fakeRandom.nextInt());

        Assertions.assertEquals(result, null);
    }
}
