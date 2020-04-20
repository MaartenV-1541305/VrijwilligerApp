/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Groep;

/**
 *
 * @author vandenboer
 */
public interface IGroepService {
    Groep findByID(long id);
    Groep save(Groep nieuwGroep);
}
