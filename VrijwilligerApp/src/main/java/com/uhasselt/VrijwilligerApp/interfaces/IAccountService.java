package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Account;

public interface IAccountService {
    Account selectAccount(String email, String password);
    Account insertAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad );

    Account inloggen(String email, String password);
    Account aanmakenAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad );
}
