package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Taak;
import com.uhasselt.VrijwilligerApp.repository.IEvenementRepository;
import com.uhasselt.VrijwilligerApp.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvenementController {
    //private IEvenementRepository evenementRepository;
    @Autowired
    private IEvenementService evenementService; //=new EvenementService(evenementRepository);

    @CrossOrigin
    @GetMapping(path = {"/evenement/{id}"})
    public void getAllEvenementen(){
        //TODO unit test
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> getGeorganiseerdEvenementen(int id)
    {
        return null;
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/taak"})
    public ResponseEntity<Taak> postTaak(@RequestBody Taak taak ){

        return new ResponseEntity<Taak>(taak, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/zoekEvenementen"})
    public ResponseEntity<List<Evenement>> zoekEvenementen(@RequestBody String naamEvenement ){

//        List<Evenement> evenementen = evenementService.findByName(naamEvenement);
//        return new ResponseEntity<List<Evenement>>(evenementen, HttpStatus.OK);

//        List<Evenement> evenementen=new ArrayList<Evenement>();
//        return new ResponseEntity<List<Evenement>>(evenementen, HttpStatus.OK);

        return null;
    }
}
