package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    public AccountService(IAccountRepository repository) {
        accountRepository = repository;
    }

    @Override
    public Account selectAccount(String email, String password) {
        return accountRepository.selectAccount(email, password);
    }

    @Override
    public Account insertAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad) {
        return accountRepository.insertAccount(email, ww, bevest_ww, nm, vnm, stad);
    }

    @Override
    public Account inloggen(String email, String password) {
        return accountRepository.inloggen(email, password);
    }

    @Override
    public Account aanmakenAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad) {
        return accountRepository.aanmakenAccount(email, ww, bevest_ww, nm, vnm, stad);
    }
}
