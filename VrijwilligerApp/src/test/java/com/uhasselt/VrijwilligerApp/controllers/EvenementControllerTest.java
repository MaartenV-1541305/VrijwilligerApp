package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import com.uhasselt.VrijwilligerApp.models.Taak;
import com.uhasselt.VrijwilligerApp.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvenementControllerTest {
    @Autowired
    private EvenementController evenementController;

    @Autowired
    private IEvenementService evenementService;
    private List<Taak> taken;
    private List<Evenement> georganiseerdeEvenementen;
    private Evenement evenement;
    private Taak taak;
    private Random fakeRandom;


    @Before
    public void setUp(){
        evenementService = Mockito.mock(EvenementService.class);
        taken = new ArrayList<>();
        taak = new Taak("taak", 5);
        evenement = new Evenement();
        evenementController = new EvenementController();
        Mockito.when(fakeRandom.nextInt()).thenReturn(0);

        georganiseerdeEvenementen.add(new Evenement());
        georganiseerdeEvenementen.add(new Evenement());
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
    public void getAllGeorganiseerdeEvenementenTest()
    {
        Mockito.when(evenementService.getAllGeorganiseerdeEvenementen(fakeRandom.nextInt())).thenReturn(georganiseerdeEvenementen);
        List<Evenement> result = evenementController.getGeorganiseerdEvenementen(fakeRandom.nextInt()).getBody();

        Assertions.assertEquals(result , georganiseerdeEvenementen);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), georganiseerdeEvenementen.size());


    }

    @Test
    public void getAllGeorganiseerdeEvenementenWithInvalidIdTest()
    {
        Mockito.when(evenementService.getAllGeorganiseerdeEvenementen(-52)).thenReturn(null);
        List<Evenement> result = evenementController.getGeorganiseerdEvenementen(-52).getBody();

        Assertions.assertEquals(result, null);

    }
}