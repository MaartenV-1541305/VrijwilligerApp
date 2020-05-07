/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Groep;
import java.util.List;

/**
 *
 * @author vandenboer
 */
public interface IGroepService {
    public Groep findByID(long id);
    public Groep save(Groep nieuwGroep);
    public List<Groep> getAllGroepen(int accountId);
    public List<Groep> findByName(String naamEvenement);
}
