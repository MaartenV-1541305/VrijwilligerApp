package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Evenement;

import java.util.List;
import java.util.Optional;

public interface IEvenementService {
    Evenement findByID(long id);
    List<Evenement> getAllGeorganiseerdeEvenementen(int accountId);
    Evenement saveEvenement(Evenement nieuwEvenement);
    List<Evenement> getEvenementen(String naamEvenement);
    List<Evenement> getEvenementen(String stad, double radius);
    Evenement getEvenement(long evenementId);
    void deleteEvenement(Evenement evenement);
}
