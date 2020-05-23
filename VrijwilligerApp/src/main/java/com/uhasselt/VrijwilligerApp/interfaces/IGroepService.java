/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Groep;
import com.uhasselt.VrijwilligerApp.models.GroepsLid;
import java.util.List;

/**
 *
 * @author vandenboer
 */
public interface IGroepService {
    public Groep findByID(long id);
    public Groep save(Groep nieuwGroep);
    public GroepsLid saveGroepsLid(GroepsLid lid);
    public void delete(Groep groep);
    public Groep edit(Groep groep);
    public Groep voegAdminToe(GroepsLid lid, Groep groep);
    public Groep verwijderAdmin(GroepsLid lid, Groep groep);
    public Groep zetEigenaar(GroepsLid lid, Groep groep);
    public List<Groep> getAllGroepenOfAccount(long accountId);
    public List<Groep> findByName(String naamEvenement);
    public List<Groep> getAllGroepen();
}
