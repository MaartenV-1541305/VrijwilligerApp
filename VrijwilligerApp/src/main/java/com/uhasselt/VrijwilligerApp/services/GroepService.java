/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uhasselt.VrijwilligerApp.repository.IGroepRepository;
import java.util.List;

@Service
/**
 *
 * @author vandenboer
 */
public class GroepService implements IGroepService {
    
    @Autowired
    private IGroepRepository groepRepository;

    public GroepService(IGroepRepository groepRepository) {
        this.groepRepository = groepRepository;
    }
    
    @Override
    public Groep findByID(long id) {
        return groepRepository.findById(id).get();
    }

    @Override
    public Groep save(Groep nieuwGroep) {
        return groepRepository.save(nieuwGroep);
    }

    @Override
    public List<Groep> getAllGroepenOfAccount(long accountId) {
        return groepRepository.getGroepenPerAccount(accountId);
    }

    @Override
    public List<Groep> findByName(String naamEvenement) {
        return groepRepository.findByName(naamEvenement);
    }
    
    @Override
    public List<Groep> getAllGroepen() {
        return groepRepository.findAll();
    }

    @Override
    public void delete(Groep groep) {
        this.groepRepository.delete(groep);
    }

    @Override
    public Groep edit(Groep groep) {
        return this.groepRepository.updateGroep(groep.getNaam(), groep.getBeschrijving(), groep.getId());
    }

    @Override
    public GroepsLid saveGroepsLid(GroepsLid lid) {
        return this.groepRepository.voegGroepslidToe(lid.getAccount(), lid.isAdmin(), lid.getGroep().getId());
    }

    @Override
    public Groep voegAdminToe(GroepsLid lid, Groep groep) {
        return this.groepRepository.voegAdminToe(lid.getId(), groep.getId());
    }

    @Override
    public Groep verwijderAdmin(GroepsLid lid, Groep groep) {
        return this.groepRepository.verwijderAdmin(lid.getId(), groep.getId());
    }

    @Override
    public Groep zetEigenaar(GroepsLid lid, Groep groep) {
        return this.groepRepository.updateEigenaar(lid, groep.getId());
    }

    @Override
    public List<GroepsLid> getAllGroepsLedenFromGroep(Groep groep) {
        return this.groepRepository.getAllGroepsLedenFromGroep(groep.getId());
    }
    
    
    
}
