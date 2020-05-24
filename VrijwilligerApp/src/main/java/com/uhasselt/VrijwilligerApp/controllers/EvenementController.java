package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Benodigheid;
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

    @Autowired
    private IEvenementService evenementService;

    public EvenementController(IEvenementService iEvenementService){
        evenementService=iEvenementService;
    }

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
    @PostMapping(path = {"/evenement/aanmaken/"})
    public ResponseEntity<Evenement> evenementAanmaken(@RequestParam("titel") String titel, @RequestParam("beschrijving") String beschrijving){
        Evenement evenement = new Evenement();
        evenement.setNaam(titel);
        evenement.setBeschrijving(beschrijving);
        Evenement result = evenementService.saveEvenement(evenement);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/bewerken/"})
    public ResponseEntity<Evenement> evenementBewerken(@RequestBody Evenement evenement){
        Evenement result = evenementService.saveEvenement(evenement);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/annuleren/"})
    public HttpStatus evenementAnnuleren(@RequestBody long evenementID){
        Evenement result = evenementService.findByID(evenementID);
        evenementService.deleteEvenement(result);

        return HttpStatus.OK;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/{evenementId}/taak"})
    public ResponseEntity<Evenement> taakAanmaken(@PathVariable("evenementId") long evenementId, @RequestBody Taak taak ) {
        Evenement evenement = evenementService.getEvenement(evenementId);
        evenement.addTaak(taak);

        evenementService.saveEvenement(evenement);

        return new ResponseEntity<Evenement>(evenement, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/{evenementId}/taak"})
    public ResponseEntity<Evenement> taakVerwijderen(@PathVariable("evenementId") long evenementId, @RequestBody Taak taak ){
        Evenement evenement = evenementService.getEvenement(evenementId);
        evenement.deleteTaak(taak);

        evenementService.saveEvenement(evenement);

        return new ResponseEntity<Evenement>(evenement, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/{evenementId}/taak"})
    public ResponseEntity<Evenement> benodigheidAanmaken(@PathVariable("evenementId") long evenementId, @RequestBody Benodigheid benodigheid ){
        Evenement evenement = evenementService.getEvenement(1);
        evenement.addBenodigheid(benodigheid);

        evenementService.saveEvenement(evenement);

        return new ResponseEntity<Evenement>(evenement, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/{evenementId}/taak"})
    public ResponseEntity<Evenement> benodigheidVerwijderen(@PathVariable("evenementId") long evenementId, @RequestBody Benodigheid benodigheid ){
        Evenement evenement = evenementService.getEvenement(1);
        evenement.deleteBenodigheid(benodigheid);

        evenementService.saveEvenement(evenement);

        return new ResponseEntity<Evenement>(evenement, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/aanmaken/groep/{groepId}"})
    public HttpStatus evenementAanmakenBeindigen(@PathVariable("groepId") long groepId){

        return HttpStatus.OK;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/bewerken/groep/{groepId}"})
    public HttpStatus evenementBewerkenBeindigen(@PathVariable("groepId") long groepId){

        return HttpStatus.OK;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/zoekEvenementen"})
    public ResponseEntity<List<Evenement>> zoekEvenementen(@RequestBody String naamEvenement ){
        List<Evenement> gevondenEvenementen = evenementService.getEvenementen(naamEvenement);

        return new ResponseEntity<List<Evenement>>(gevondenEvenementen,HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/zoekEvenementen"})
    public ResponseEntity<List<Evenement>> zoekEvenementen(@RequestBody String stad,double radius ){
        List<Evenement> gevondenEvenementen = evenementService.getEvenementen(stad,radius);

        return new ResponseEntity<List<Evenement>>(gevondenEvenementen,HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/detailsEvenementOpvragen"})
    public ResponseEntity<Evenement> detailsEvenementOpvragen(@RequestBody long evenementId ){
        Evenement gevondenEvenement = evenementService.getEvenement(evenementId);

        return new ResponseEntity<Evenement>(gevondenEvenement,HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/evenement/vraagToestemming"})
    public ResponseEntity vraagToestemming(@RequestBody long evenementId ){
        evenementService.getEvenement(evenementId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
