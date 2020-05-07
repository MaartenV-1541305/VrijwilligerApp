/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 *
 * @author vandenboer
 */
public class GroepController {
    @Autowired
    private IGroepService groepService;

    @CrossOrigin
    @GetMapping(path = {"/groep"})
    public void getAllGroepen(){
        //TODO unit test
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    public ResponseEntity<Groep> nieuwGroep(String groepsNaam, Account eigenaar) {
        //TODO: Check groepsnaam bestaat al
        Groep groep = new Groep();
        GroepsLid eigenaarLid = new GroepsLid();
        eigenaarLid.setAccount(eigenaar);
        eigenaarLid.setAdmin(true);
        groep.setNaam(groepsNaam);
        groep.setMaker(eigenaarLid);
        groep.getLeden().add(eigenaarLid);

        return new ResponseEntity<>(groep, HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    @SuppressWarnings("null")
    public ResponseEntity<Groep> voegGroepsLidToe(Groep groep, Account account) {
        GroepsLid lid = new GroepsLid();
        lid.setAccount(account);
        groep.getLeden().add(lid);
        
        return new ResponseEntity<>(groep, HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    @SuppressWarnings("null")
    public ResponseEntity<Groep> zetBeschrijving(Groep groep, String beschrijving) {
        groep.setBeschrijving(beschrijving);
        
        return new ResponseEntity<>(groep, HttpStatus.OK);
    }
    
    public ResponseEntity<Groep> getGroepById(long id) {
        //TODO: GET GROEP
        return null;
    }
    
    public void groepAanmakenBeeindigen(long groepId) {
        //TODO: stuur afrondings bericht
    }
    
}
