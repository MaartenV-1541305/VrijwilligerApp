package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvenementService implements IEvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public Evenement findByID(long id) {
        return evenementRepository.findById(id).get();
    }

    @Override
    public Evenement save(Evenement nieuwEvenement) {
        return evenementRepository.save(nieuwEvenement);
    }
}
