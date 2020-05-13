package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Benodigheid;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Taak;
import com.uhasselt.VrijwilligerApp.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EvenementControllerTest {
    @Autowired
    private EvenementController evenementController;

    @Autowired
    private IEvenementService evenementService;
    private List<Taak> taken;
    private List<Evenement> georganiseerdeEvenementen;
    private List<Evenement> gevondenEvenementen;
    private Evenement gevondenEvenement;
    private Evenement evenement;
    private Taak taak;
    private Benodigheid benodigheid;
    private Random fakeRandom;


    @Before
    public void setUp(){
        evenementService = Mockito.mock(EvenementService.class);
        taken = new ArrayList<>();
        taak = new Taak("taak", 5);
        benodigheid = new Benodigheid();
        evenement = new Evenement();
        evenementController = new EvenementController(evenementService);
        fakeRandom = Mockito.mock(Random.class);
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        georganiseerdeEvenementen = new ArrayList<Evenement>();
        georganiseerdeEvenementen.add(new Evenement());
        georganiseerdeEvenementen.add(new Evenement());

        gevondenEvenementen = new ArrayList<Evenement>();
        gevondenEvenementen.add(new Evenement());
        gevondenEvenementen.add(new Evenement());

        gevondenEvenement=new Evenement();
    }

    @Test
    public void getAllGeorganiseerdeEvenementenTest()
    {
        Mockito.when(evenementService.getAllGeorganiseerdeEvenementen(fakeRandom.nextInt())).thenReturn(georganiseerdeEvenementen);
        List<Evenement> result = evenementController.getGeorganiseerdEvenementen(fakeRandom.nextInt()).getBody();

        Assertions.assertEquals(result , georganiseerdeEvenementen);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), georganiseerdeEvenementen.size());

    }

    @Test
    public void evenementAanmakenTest(){
        String titel = "titel";
        String beschrijving = "beschrijving";

        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.evenementAanmaken(titel, beschrijving);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotEquals(Objects.requireNonNull(result.getBody()).getId(), 0);
        Assertions.assertEquals(result.getBody().getNaam(), titel);
        Assertions.assertEquals(result.getBody().getBeschrijving(), beschrijving);
    }

    @Test
    public void evenementBewerkenTest(){
        Evenement evenement = new Evenement();

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.evenementBewerken(evenement);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertEquals(result.getBody().getNaam(), "nieuwe naam");
        Assertions.assertEquals(result.getBody().getBeschrijving(), "nieuwe beschrijving");
    }

    @Test
    public void evenementAnnuleren(){
        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        HttpStatus result = evenementController.evenementAnnuleren(1);

        Assertions.assertEquals(result.value(), 200);
    }

    @Test
    public void postTaakTest() throws Exception {
        Evenement evenement = new Evenement();

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.taakAanmaken(1, taak);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getTaken().size(),1);
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getTaken().get(0),taak);
    }

    @Test
    public void deleteTaakTest() throws Exception {
        Evenement evenement = new Evenement();
        Taak taak = new Taak();
        evenement.addTaak(taak);
        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.taakVerwijderen(1, taak);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getTaken().size(),0);
    }

    @Test
    public void postBenodigheidTest() throws Exception {
        Evenement evenement = new Evenement();

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.benodigheidAanmaken(1, benodigheid);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getBenodigheden().size(),1);
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getBenodigheden().get(0), benodigheid);
    }

    @Test
    public void deleteBenodigheidTest() throws Exception {
        Evenement evenement = new Evenement();
        Benodigheid benodigheid = new Benodigheid();
        evenement.addBenodigheid(benodigheid);

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.benodigheidVerwijderen(1, benodigheid);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertEquals(Objects.requireNonNull(result.getBody()).getBenodigheden().size(), 0);
    }

    @Test
    public void evenementAanmakenBeindigenTest(){
        HttpStatus result = evenementController.evenementAanmakenBeindigen(1);
        //TODO groepId test ?
        Assertions.assertEquals(result.value(), 200);
    }

    @Test
    public void evenementBewerkenBeindigenTest(){
        HttpStatus result = evenementController.evenementBewerkenBeindigen(1);
        //TODO groepId test ?
        Assertions.assertEquals(result.value(), 200);
    }

    @Test
    public void getAllGeorganiseerdeEvenementenWithInvalidIdTest()
    {
        Mockito.when(evenementService.getAllGeorganiseerdeEvenementen(-52)).thenReturn(null);
        List<Evenement> result = evenementController.getGeorganiseerdEvenementen(-52).getBody();

        Assertions.assertEquals(result, null);

    }

    @Test
    public void zoekEvenementenOpNaamTest(){
        Mockito.when(evenementService.getEvenementen("test")).thenReturn(gevondenEvenementen);
        List<Evenement> result = evenementController.zoekEvenementen("test").getBody();
        Assertions.assertEquals(result, gevondenEvenementen);
    }

    @Test
    public void zoekEvenementenOpStadEnRadiusTest(){
        Mockito.when(evenementService.getEvenementen("stad",150)).thenReturn(gevondenEvenementen);
        List<Evenement> result = evenementController.zoekEvenementen("stad",150).getBody();
        Assertions.assertEquals(result, gevondenEvenementen);
    }

    @Test
    public void detailsEvenementOpvragenTest(){
        Mockito.when(evenementService.getEvenement(1)).thenReturn(gevondenEvenement);
        Evenement result = evenementController.detailsEvenementOpvragen(1).getBody();
        Assertions.assertEquals(result, gevondenEvenement);
    }

}