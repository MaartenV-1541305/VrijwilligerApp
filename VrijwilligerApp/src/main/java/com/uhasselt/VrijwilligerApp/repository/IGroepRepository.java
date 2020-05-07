/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.repository;

import com.uhasselt.VrijwilligerApp.models.Groep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
/**
 *
 * @author vandenboer
 */
public interface IGroepRepository extends JpaRepository<Groep, Long> {
    
    @Query("Select g from Groep e where g.naam like %:groepsNaam%")
    List<Groep> findByName(String groepsNaam);

    List<Groep> getGroepen(int groepId);
    
}
