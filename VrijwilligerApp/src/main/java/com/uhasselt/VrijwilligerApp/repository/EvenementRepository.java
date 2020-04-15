package com.uhasselt.VrijwilligerApp.repository;

import com.uhasselt.VrijwilligerApp.models.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
