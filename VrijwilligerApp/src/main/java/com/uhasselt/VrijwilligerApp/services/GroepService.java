/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IGroepService;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.repository.GroepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 *
 * @author vandenboer
 */
public class GroepService implements IGroepService {
    
    @Autowired
    private GroepRepository groepRepository;

    @Override
    public Groep findByID(long id) {
        return groepRepository.findById(id).get();
    }

    @Override
    public Groep save(Groep nieuwGroep) {
        return groepRepository.save(nieuwGroep);
    }
    
}
