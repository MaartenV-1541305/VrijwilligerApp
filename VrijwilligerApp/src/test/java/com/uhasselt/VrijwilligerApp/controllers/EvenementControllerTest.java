package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Account;
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
    private Evenement evenement2;
    private Taak taak;
    private Benodigheid benodigheid;
    private Random fakeRandom;
    private Account organisator;


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
        organisator = new Account();
        organisator.setNaam("organisator");

        georganiseerdeEvenementen = new ArrayList<Evenement>();
        evenement.setAccountOrganisator(organisator);
        evenement2.setAccountOrganisator(organisator);
        georganiseerdeEvenementen.add(evenement);
        georganiseerdeEvenementen.add(evenement2);

        gevondenEvenementen = new ArrayList<Evenement>();
        gevondenEvenementen.add(new Evenement());
        gevondenEvenementen.add(new Evenement());

        gevondenEvenement=new Evenement();

    }

    @Test
    public void getAllGeorganiseerdeEvenementenTest()
    {
        Mockito.when(evenementService.getAllGeorganiseerdeEvenementen(fakeRandom.nextInt())).thenReturn(georganiseerdeEvenementen);
        ResponseEntity<List<Evenement>> responseEntityResult = evenementController.getGeorganiseerdEvenementen(fakeRandom.nextInt());
        List<Evenement> result = responseEntityResult.getBody();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(responseEntityResult.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(result , georganiseerdeEvenementen);
        Assertions.assertEquals(result.size(), georganiseerdeEvenementen.size());
        Assertions.assertEquals(result.get(0).getAccountOrganisator().getNaam(), "organisator");
        Assertions.assertEquals(result.get(1).getAccountOrganisator().getNaam(), "organisator");

    }

  
    @Test
    public void evenementAanmakenTest(){
        String titel = "titel";
        String beschrijving = "beschrijving";

        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.evenementAanmaken(titel, beschrijving);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertNotEquals(result.getBody().getId(), 0);
        Assertions.assertEquals(result.getBody().getNaam(), titel);
        Assertions.assertEquals(result.getBody().getBeschrijving(), beschrijving);
    }

    @Test
    public void evenementAanmakenMetLegeNaamTest() {
        ResponseEntity<Evenement> result = evenementController.evenementAanmaken(null, "beschrijving");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void evenementAanmakenMetLegeBeschrijvingTest() {
        ResponseEntity<Evenement> result = evenementController.evenementAanmaken("titel", null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void evenementAanmakenMetLegeNaamEnBeschrijving(){
        ResponseEntity<Evenement> result = evenementController.evenementAanmaken(null, null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void evenementBewerkenTest(){
        Evenement evenement = new Evenement();
        evenement.setBeschrijving("nieuwe beschrijving");
        evenement.setNaam("nieuwe naam");

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.evenementBewerken(evenement);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(result.getBody().getNaam(), "nieuwe naam");
        Assertions.assertEquals(result.getBody().getBeschrijving(), "nieuwe beschrijving");
    }

    @Test
    public void evenementBewerkenLeegEvenementTest() {
        ResponseEntity<Evenement> result = evenementController.evenementBewerken(null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void evenementAnnuleren(){
        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        HttpStatus result = evenementController.evenementAnnuleren(1);

        Assertions.assertEquals(result.value(), 200);
    }

    @Test
    public void postTaakTest() {
        Evenement evenement = new Evenement();

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.taakAanmaken(1, taak);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertNotEquals(result.getBody().getTaken().size(),0);
        Assertions.assertTrue(result.getBody().getTaken().contains(taak));
    }

    @Test
    public void postTaakMetLegeTaakTest(){
        ResponseEntity<Evenement> result = evenementController.taakAanmaken(1,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void postTaakMetVerkeerdIdTest(){
        ResponseEntity<Evenement> result = evenementController.taakAanmaken(0,taak);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void postTaakMetVerkeerdIdEnLegeTaakTest(){
        ResponseEntity<Evenement> result = evenementController.taakAanmaken(0,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void deleteTaakTest()   {
        Evenement evenement = new Evenement();
        Taak taak = new Taak();
        evenement.addTaak(taak);
        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.taakVerwijderen(1, taak);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertFalse(result.getBody().getTaken().contains(taak));
    }

    @Test
    public void deleteTaakMetLegeTaakTest(){
        ResponseEntity<Evenement> result = evenementController.taakVerwijderen(1,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void deleteTaakMetVerkeerdEvenementIdTest(){
        ResponseEntity<Evenement> result = evenementController.taakVerwijderen(0,taak);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void deleteTaakMetLegeTaakEnVerkeerdEvenementIdTest(){
        ResponseEntity<Evenement> result = evenementController.taakVerwijderen(0,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void postBenodigheidTest()   {
        Evenement evenement = new Evenement();

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.benodigheidAanmaken(1, benodigheid);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertNotEquals(result.getBody().getBenodigheden().size(),0);
        Assertions.assertTrue(result.getBody().getBenodigheden().contains(benodigheid));
    }

    @Test
    public void postBenodigheidMetLegeBenodigheidTest(){
        ResponseEntity<Evenement> result = evenementController.benodigheidAanmaken(1,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void postBenodigheidMetVerkeerdIdTest(){
        ResponseEntity<Evenement> result = evenementController.benodigheidAanmaken(0,benodigheid);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }
    @Test
    public void postBenodigheidMetLegeBenodigheidEnVerkeerdIdTest(){
        ResponseEntity<Evenement> result = evenementController.benodigheidAanmaken(0,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void deleteBenodigheidTest()   {
        Evenement evenement = new Evenement();
        Benodigheid benodigheid = new Benodigheid();
        evenement.addBenodigheid(benodigheid);

        Mockito.when(evenementService.getEvenement(1)).thenReturn(evenement);
        Mockito.when(evenementService.saveEvenement(evenement)).thenReturn(evenement);

        ResponseEntity<Evenement> result = evenementController.benodigheidVerwijderen(1, benodigheid);

        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertNotNull(result.getBody());
        Assertions.assertFalse(result.getBody().getBenodigheden().contains(benodigheid));
    }

    @Test
    public void deleteBenodigheidMetLegeBenodigheidTest(){
        ResponseEntity<Evenement> result = evenementController.benodigheidVerwijderen(1,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void deleteBenodigheidMetVerkeerdIdTest(){
        ResponseEntity<Evenement> result = evenementController.benodigheidVerwijderen(0,benodigheid);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void deleteBenodigheidMetLegeBenodigheidEnVerkeerdIdTest(){
        ResponseEntity<Evenement> result = evenementController.benodigheidVerwijderen(0,null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCodeValue(), 422);
    }

    @Test
    public void evenementAanmakenBeindigenTest(){
        HttpStatus result = evenementController.evenementAanmakenBeindigen(1);

        Assertions.assertEquals(result.value(), 200);
    }

    @Test
    public void evenementBewerkenBeindigenTest(){
        HttpStatus result = evenementController.evenementBewerkenBeindigen(1);

        Assertions.assertEquals(result.value(), 200);
    }

    @Test
    public void getAllGeorganiseerdeEvenementenWithInvalidIdTest()
    {
        Mockito.when(evenementService.getAllGeorganiseerdeEvenementen(-52)).thenReturn(null);
        List<Evenement> result = evenementController.getGeorganiseerdEvenementen(-52).getBody();

        Assertions.assertNull(result);
    }

    @Test
    public void zoekEvenementenOpNaamTest(){
        Mockito.when(evenementService.getEvenementen("test")).thenReturn(gevondenEvenementen);
        ResponseEntity<List<Evenement>> response = evenementController.zoekEvenementen("test");
        List<Evenement> result = response.getBody();
        int statusCode = response.getStatusCode().value();
        Assertions.assertEquals(result, gevondenEvenementen);
        Assertions.assertEquals(result.size(), gevondenEvenementen.size());
        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void zoekEvenementenOpStadEnRadiusTest(){
        Mockito.when(evenementService.getEvenementen("stad",150)).thenReturn(gevondenEvenementen);
        ResponseEntity<List<Evenement>> response = evenementController.zoekEvenementen("stad",150);
        List<Evenement> result = response.getBody();
        int statusCode = response.getStatusCode().value();
        Assertions.assertEquals(result, gevondenEvenementen);
        Assertions.assertEquals(result.size(), gevondenEvenementen.size());
        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void detailsEvenementOpvragenTest(){
        Mockito.when(evenementService.getEvenement(1)).thenReturn(gevondenEvenement);
        ResponseEntity<Evenement> response = evenementController.detailsEvenementOpvragen(1);
        Evenement result = response.getBody();
        int statusCode = response.getStatusCode().value();
        Assertions.assertEquals(result, gevondenEvenement);
        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void vraagToestemmingTest(){
        Mockito.when(evenementService.getEvenement(1)).thenReturn(gevondenEvenement);
        ResponseEntity response= evenementController.vraagToestemming(1);
        int statusCode= response.getStatusCode().value();
        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void vraagToestemmingWithInvalidIdTest()
    {
        Mockito.when(evenementService.getEvenement(-52)).thenReturn(null);
        ResponseEntity response= evenementController.vraagToestemming(-52);
        int statusCode= response.getStatusCode().value();
        Assertions.assertEquals(400,statusCode);

    }

}