package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Evenement;

import java.util.Optional;

public interface IEvenementService {
    Evenement findByID(long id);
    Evenement save(Evenement nieuwEvenement);
}
