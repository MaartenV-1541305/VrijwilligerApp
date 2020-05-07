package com.uhasselt.VrijwilligerApp.interfaces;

import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Evenement;

public interface IAccountService {
    Account save(Account nieuwAccount);
    Account selectAccount(String email, String password);
    void insertAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad );
}
