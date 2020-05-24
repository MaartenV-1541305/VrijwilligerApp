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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public GroepController(IGroepService groepService) {
        this.groepService = groepService;
    }
    
    @CrossOrigin
    @ResponseBody
    @GetMapping(path = {"/groep"})
    public ResponseEntity<List<Groep>> getAllGroepen() {
        return new ResponseEntity<>(this.groepService.getAllGroepen(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    public ResponseEntity<Groep> nieuwGroep(String groepsNaam, Account eigenaar) {
        
        //TODO: Check groepsnaam bestaat al
        List<Groep> result = groepService.findByName(groepsNaam);
        if (result.stream().anyMatch((g) -> {
            return g.getNaam().equals(groepsNaam);
        })) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        
        Groep groep = new Groep();
        GroepsLid eigenaarLid = new GroepsLid();
        eigenaarLid.setAccount(eigenaar);
        eigenaarLid.setAdmin(true);
        groep.setNaam(groepsNaam);
        groep.setMaker(eigenaarLid);
        groep.getLeden().add(eigenaarLid);

        return new ResponseEntity<>(this.groepService.save(groep), HttpStatus.OK);
    }
    
    public ResponseEntity<Groep> zetEigenaar(Groep groep, GroepsLid lid) {
        groep.setMaker(lid);
        
        return new ResponseEntity<>(this.groepService.zetEigenaar(lid, groep), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    @SuppressWarnings("null")
    public ResponseEntity<Groep> zetBeschrijving(Groep groep, String beschrijving) {
        groep.setBeschrijving(beschrijving);
        
        return new ResponseEntity<>(this.groepService.edit(groep), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    @SuppressWarnings("null")
    public ResponseEntity<Groep> zetNaam(Groep groep, String naam) {
        groep.setNaam(naam);
        
        return new ResponseEntity<>(this.groepService.edit(groep), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @GetMapping(path = {"/groep"})
    public ResponseEntity<Groep> getGroepById(long id) {
        return new ResponseEntity<>(this.groepService.findByID(id), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @GetMapping(path = {"/groep"})
    public ResponseEntity<Groep> groepAanmakenBeeindigen(long groepId) {
        return new ResponseEntity<>(this.groepService.findByID(groepId), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    @SuppressWarnings("null")
    public ResponseEntity<Groep> voegGroepsLidToe(Groep groep, Account account) {
        if (isAccountLidVanGroep(groep, account)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        
        GroepsLid lid = new GroepsLid();
        lid.setAccount(account);
        groep.getLeden().add(lid);
        
        return new ResponseEntity<>(this.groepService.saveGroepsLid(lid).getGroep(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    public ResponseEntity<Groep> voegAdminToe(Groep groep, Account account) {
        if (isAccountLidVanGroep(groep, account)) {
            return new ResponseEntity<>(this.groepService.voegAdminToe(getGroepsLidFromGroep(account, groep), groep), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    public ResponseEntity<Groep> verwijderAdmin(Groep groep, Account account) {
        if (isAccountLidVanGroep(groep, account)) {
            return new ResponseEntity<>(this.groepService.verwijderAdmin(getGroepsLidFromGroep(account, groep), groep), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    public ResponseEntity<Groep> voegAdminToe(Groep groep, GroepsLid lid) {
        if (groep.equals(lid.getGroep())) {
            return new ResponseEntity<>(this.groepService.voegAdminToe(lid, groep), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/groep"})
    public ResponseEntity<Groep> verwijderAdmin(Groep groep, GroepsLid lid) {
        if (groep.equals(lid.getGroep())) {
            return new ResponseEntity<>(this.groepService.verwijderAdmin(lid, groep), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    private GroepsLid getGroepsLidFromGroep(Account account, Groep groep) { 
        for (GroepsLid lid : groep.getLeden()) {
            if (lid.getAccount().equals(account)) {
                return lid;
            }
        }
        throw new AssertionError("Account: " + account + " geen lid van groep: " + groep);
    }
    
    private boolean isAccountLidVanGroep(Groep groep, Account account) {
        return this.groepService.getAllGroepenOfAccount(account.getId()).stream().anyMatch((g) -> {
            return g.equals(groep);
        });
    }
    
}
