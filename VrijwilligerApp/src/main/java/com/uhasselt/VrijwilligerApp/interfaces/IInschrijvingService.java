package com.uhasselt.VrijwilligerApp.interfaces;


import com.uhasselt.VrijwilligerApp.models.Inschrijving;

import java.util.List;

public interface IInschrijvingService {
    List<Inschrijving> getAllInschrijvingenByAccountId(int id);
    boolean deleteInschrijving(int inschrijvingsId);

}
