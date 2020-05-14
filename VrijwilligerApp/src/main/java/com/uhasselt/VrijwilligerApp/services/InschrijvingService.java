package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IInschrijvingService;
import com.uhasselt.VrijwilligerApp.models.Benodigheid;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import com.uhasselt.VrijwilligerApp.models.Taak;
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

    @Override
    public Inschrijving koppelInschrijvingMetAccountEnEvenement(long accountId, long EvenementId) {
        Inschrijving inschrijving = new Inschrijving();
        //inschrijving.setAccount();
        //inschrijving.setEvenement();
        return inschrijvingRepository.save(inschrijving);
    }

    @Override
    public Inschrijving voegTaakToe(Taak taak, long inschrijvingId) {
        Inschrijving inschrijving = inschrijvingRepository.findById(inschrijvingId).get();

        return inschrijvingRepository.save(inschrijving);
    }

    @Override
    public Inschrijving verwijderTaak(long taakId, long inschrijvingId) {
        Inschrijving inschrijving = inschrijvingRepository.findById(inschrijvingId).get();

        return inschrijvingRepository.save(inschrijving);
    }

    @Override
    public Inschrijving voegBenodigheidToe(Benodigheid benodigheid, long inschrijvingId) {
        Inschrijving inschrijving = inschrijvingRepository.findById(inschrijvingId).get();

        return inschrijvingRepository.save(inschrijving);
    }

    @Override
    public Inschrijving verwijderBenodigheid(long benodigheidId, long inschrijvingId) {
        Inschrijving inschrijving = inschrijvingRepository.findById(inschrijvingId).get();

        return inschrijvingRepository.save(inschrijving);
    }

    @Override
    public Inschrijving schrijfInVoorEvenement(long groepID, long inschrijvingId) {
        Inschrijving inschrijving = inschrijvingRepository.findById(inschrijvingId).get();

        return inschrijvingRepository.save(inschrijving);
    }
}
