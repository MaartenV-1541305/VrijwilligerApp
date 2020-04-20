package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Taak;
import com.uhasselt.VrijwilligerApp.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

public class EvenementControllerTest {
    @Autowired
    private EvenementController evenementController;

    @Autowired
    private IEvenementService evenementService;
    private List<Taak> taken;
    private Evenement evenement;
    private Taak taak;

    @Before
    public void setUp(){
        evenementService = Mockito.mock(EvenementService.class);
        taken = new ArrayList<>();
        taak = new Taak("taak", 5);
        evenement = new Evenement();
        evenementController = new EvenementController();
    }


    @Test
    public void postTaakTest() throws Exception {
        int status = evenementController.postTaak(taak).getStatusCode().value();
        Taak result = evenementController.postTaak(taak).getBody();
        //TODO voeg taak aan evenement toe, save of update nieuw evenement en check of taak toegevoegd is
        Assertions.assertEquals(status,200);
        assert result != null;
        Assertions.assertEquals(result.getAantal(), 5);
    }

    @Test
    public void zoekEvenementenTest() throws Exception {
        ResponseEntity<List<Evenement>> responseEntity = evenementController.zoekEvenementen("test");
        int status = responseEntity.getStatusCode().value();
        List<Evenement> result = responseEntity.getBody();

        Assertions.assertEquals(status,200);
        assert result != null;
        Assertions.assertEquals(result.size(), 0);
    }

}