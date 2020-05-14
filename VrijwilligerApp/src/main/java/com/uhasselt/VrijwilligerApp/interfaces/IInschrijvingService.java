package com.uhasselt.VrijwilligerApp.interfaces;


import com.uhasselt.VrijwilligerApp.models.Inschrijving;

import java.util.List;

public interface IInschrijvingService {
    List<Inschrijving> getAllInschrijvingenByAccountId(int id);
    Inschrijving getInschrijving(int inschrijvingsId);
    Inschrijving deleteInschrijving(int inschrijvingsId);
}

