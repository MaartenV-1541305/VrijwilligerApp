package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountEvenement implements IAccountService {
    @Autowired
    private IAccountRepository IAccountRepository;

    @Override
    public Account selectAccount(String email, String password) {
        return null;
    }

    @Override
    public Account insertAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad) {

        return null;
    }

    @Override
    public Account inloggen(String email, String password) {
        return null;
    }

    @Override
    public Account aanmakenAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad) {
        return null;
    }

}
