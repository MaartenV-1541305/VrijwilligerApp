package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvenementController {
    @Autowired
    private IEvenementService evenementService;

    @CrossOrigin
    @GetMapping("/evenement")
    public void getAllEvenementen(){
        //TODO unit test
    }

}
