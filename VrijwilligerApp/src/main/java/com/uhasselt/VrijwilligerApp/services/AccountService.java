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
    public Account save(Account nieuwAccount) {
        return null;
    }

    @Override
    public Account selectAccount(String email, String password) {
        return accountRepository.selectAccount(email, password);
    }
/*
    @Override
    public Account selectAccount(String email, String password) {
        Account acc = new Account();
        acc.setEmail("test@test.be");
        acc.setPassword("test");
        acc.setNaam("testNaam");
        return acc;
    }
*/
    @Override
    public void insertAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad) {

    }
}
