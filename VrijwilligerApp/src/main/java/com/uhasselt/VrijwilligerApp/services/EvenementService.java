package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IEvenementService;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Evenement> getAllGeorganiseerdeEvenementen(int accountId) {
        return null;
    }

    @Override
    public Evenement save(Evenement nieuwEvenement) {
        return evenementRepository.save(nieuwEvenement);
    }

    @Override
    public List<Evenement> findByName(String naamEvenement) {
        return evenementRepository.findByName(naamEvenement);
    }
}
