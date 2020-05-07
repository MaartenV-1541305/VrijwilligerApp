/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Groep;
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
    public List<Groep> getAllGroepen(int accountId) {
        return groepRepository.getGroepen(accountId);
    }

    @Override
    public List<Groep> findByName(String naamEvenement) {
        return groepRepository.findByName(naamEvenement);
    }
    
}
