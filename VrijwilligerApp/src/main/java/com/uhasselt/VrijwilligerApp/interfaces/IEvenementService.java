package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Evenement;

import java.util.List;
import java.util.Optional;

public interface IEvenementService {
    Evenement findByID(long id);
    List<Evenement> getAllGeorganiseerdeEvenementen(int accountId);
    Evenement saveEvenement(Evenement nieuwEvenement);
    List<Evenement> getEvenementen(String naamEvenement);
    Evenement getEvenement(long evenementId);
}
