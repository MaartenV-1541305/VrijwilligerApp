package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.models.Taak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvenementController {
    @Autowired
    private IEvenementService evenementService;

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

}
