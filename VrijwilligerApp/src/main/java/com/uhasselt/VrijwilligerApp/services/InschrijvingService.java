package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IInschrijvingService;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import com.uhasselt.VrijwilligerApp.repository.IInschrijvingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InschrijvingService implements IInschrijvingService {
    @Autowired
    private IInschrijvingRepository inschrijvingRepository;

    public InschrijvingService(IInschrijvingRepository repository){
        this.inschrijvingRepository = repository;
    }

    @Override
    public List<Inschrijving> getAllInschrijvingenByAccountId(int id) {
        return inschrijvingRepository.getAllInschrijvingen(id) ;
    }
}
