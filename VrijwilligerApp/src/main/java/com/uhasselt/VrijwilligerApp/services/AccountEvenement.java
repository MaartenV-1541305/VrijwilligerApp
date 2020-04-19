package com.uhasselt.VrijwilligerApp.services;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import com.uhasselt.VrijwilligerApp.repository.AccountRepository;
import com.uhasselt.VrijwilligerApp.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountEvenement implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account save(Account nieuwAccount) {

        return accountRepository.save(nieuwAccount);
    }

}
