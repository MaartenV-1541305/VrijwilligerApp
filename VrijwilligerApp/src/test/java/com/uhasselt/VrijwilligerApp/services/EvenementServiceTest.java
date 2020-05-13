package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.repository.IEvenementRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EvenementServiceTest {

    @Autowired
    private IEvenementService evenementService;

    @Autowired
    private IEvenementRepository repository;
    private Random fakeRandom;
    private List<Evenement> georganiseerdeEvenementen;
    private List<Evenement> gevondenEvenementen;
    private Evenement gevondenEvenement;
    private Evenement nieuwEvenement;
    private Evenement bewerktEvenement;

    @Before
    public void setUp(){

        repository = Mockito.mock(IEvenementRepository.class);
        evenementService = new EvenementService(repository);
        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        georganiseerdeEvenementen = new ArrayList<>();
        Evenement e1 = new Evenement();
        e1.setNaam("evenement1");
        Evenement e2 = new Evenement();
        e2.setNaam("evenement2");
        georganiseerdeEvenementen.add(e1);
        georganiseerdeEvenementen.add(e2);

        gevondenEvenementen = new ArrayList<>();
        Evenement e3 = new Evenement();
        e1.setNaam("evenement1");
        Evenement e4 = new Evenement();
        e2.setNaam("evenement2");
        gevondenEvenementen.add(e1);
        gevondenEvenementen.add(e2);

        gevondenEvenement=new Evenement();

        nieuwEvenement = new Evenement();
        nieuwEvenement.setNaam("nieuw evenement");
        nieuwEvenement.setBeschrijving("beschrijving evenement");

        bewerktEvenement = new Evenement();
        bewerktEvenement.setNaam("bewerkt evenement");
        bewerktEvenement.setBeschrijving("beschrijving bewerkt");

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

        List<Evenement> result = evenementService.getAllGeorganiseerdeEvenementen(-58494468);

        Assertions.assertEquals(result, null);
    }


    @Test
    public void getEvenementenTest(){
        Mockito.when(repository.selectEvenementen("test")).thenReturn(gevondenEvenementen);

        List<Evenement> result = evenementService.getEvenementen("test");

        Assertions.assertEquals(result, gevondenEvenementen);
    }

    @Test
    public void getEvenementTest(){
        Mockito.when(repository.selectEvenement(1)).thenReturn(gevondenEvenement);

        Evenement result = evenementService.getEvenement(1);

        Assertions.assertEquals(result, gevondenEvenement);
    }

    @Test
    public void insertEvenement(){
        Mockito.when(repository.save(nieuwEvenement)).thenReturn(nieuwEvenement);

        Evenement result = evenementService.saveEvenement(nieuwEvenement);

        Assertions.assertNotEquals(result.getId(), 0L);
        Assertions.assertEquals(result.getNaam(), "nieuw evenement");
        Assertions.assertEquals(result.getBeschrijving(), "beschrijving evenement");
    }

    @Test
    public void updateEvenement(){
        Mockito.when(repository.save(nieuwEvenement)).thenReturn(nieuwEvenement);
        Mockito.when(repository.save(bewerktEvenement)).thenReturn(bewerktEvenement);

        Evenement resultOld = evenementService.saveEvenement(nieuwEvenement);
        Evenement resultUpdate = evenementService.saveEvenement(bewerktEvenement);

        Assertions.assertEquals(resultOld.getId(), resultUpdate.getId());
        Assertions.assertNotEquals(resultUpdate.getBeschrijving(), resultOld.getBeschrijving());
        Assertions.assertNotEquals(resultUpdate.getNaam(), resultOld.getNaam());
    }

}
