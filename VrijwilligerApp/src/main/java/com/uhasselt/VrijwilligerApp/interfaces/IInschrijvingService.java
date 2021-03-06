package com.uhasselt.VrijwilligerApp.interfaces;


import com.uhasselt.VrijwilligerApp.models.Benodigheid;
import com.uhasselt.VrijwilligerApp.models.Inschrijving;
import com.uhasselt.VrijwilligerApp.models.Taak;

import java.util.List;

public interface IInschrijvingService {
    List<Inschrijving> getAllInschrijvingenByAccountId(int id);
    boolean deleteInschrijving(int inschrijvingsId);
    Inschrijving koppelInschrijvingMetAccountEnEvenement(long accountId, long EvenementId);
    Inschrijving voegTaakToe(Taak taak, long inschrijvingId);
    Inschrijving verwijderTaak(long taakId, long inschrijvingId);
    Inschrijving voegBenodigheidToe(Benodigheid benodigheid, long inschrijvingId);
    Inschrijving verwijderBenodigheid(long benodigheidId, long inschrijvingId);
    Inschrijving schrijfInVoorEvenement(long groepID, long inschrijvingId);
    Inschrijving getInschrijving(int inschrijvingsId);
    List<Inschrijving> getAanwezigheden(long vrijwilligersId);
    boolean putAanwezigheid(long inschrijvingsId,boolean aanwezigheid);
    boolean putScore(long vrijwilligersId, double score);
}

