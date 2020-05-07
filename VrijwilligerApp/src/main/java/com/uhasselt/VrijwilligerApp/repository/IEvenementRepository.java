package com.uhasselt.VrijwilligerApp.repository;

import com.uhasselt.VrijwilligerApp.models.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEvenementRepository extends JpaRepository<Evenement, Long> {

    List<Evenement> getGeorganiseerdeEvenementen(int accountId);

    List<Evenement> selectEvenementen(String naamEvenement);

}
