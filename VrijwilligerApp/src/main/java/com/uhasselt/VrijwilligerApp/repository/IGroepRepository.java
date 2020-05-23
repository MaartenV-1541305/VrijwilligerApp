/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.repository;

import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
/**
 *
 * @author vandenboer
 */
public interface IGroepRepository extends JpaRepository<Groep, Long> {
    
    @Query("Select * from Groep g where g.naam like ?1")
    List<Groep> findByName(String groepsNaam);
    
    @Query("Select Groep from GroepsLid g where g.account = ?1")
    List<Groep> getGroepenPerAccount(long accountId);
    
    @Modifying
    @Query("update Groep g set g.naam = ?1, g.beschrijving = ?2 where g.id = ?3")
    Groep updateGroep(String name, String beschrijving, long groepId);
    
    @Modifying
    @Query("update Groep g set g.beschrijving = ?1 where g.id = ?2")
    Groep updateBeschrijving(String beschrijving, long groepId);
    
    @Modifying
    @Query("update Groep g set g.naam = ?1, where g.id = ?2")
    Groep updateNaam(String naam, long groepId);
    
    @Modifying
    @Query("update Groep g set g.maker = ?1, where g.id = ?2")
    Groep updateEigenaar(GroepsLid eigenaar, long groepId);
    
    @Query("insert into groepslid g values (?1 , ?3 , ?2)")
    GroepsLid voegGroepslidToe(Account account, boolean isAdmin, long groepId);
    
    @Modifying
    @Query("update groepslid g set g.isAdmin = 1 where g.id = ?2")
    Groep voegAdminToe(long groepsLidId , long groepId);
    
    @Modifying
    @Query("update groepslid g set g.isAdmin = 0 where g.id = ?2")
    Groep verwijderAdmin(long groepsLidId , long groepId);
    
}
