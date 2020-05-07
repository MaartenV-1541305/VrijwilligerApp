package com.uhasselt.VrijwilligerApp.repository;

import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT account FROM Account a WHERE a.email like %:email% AND a.password LIKE %:wachtwoord%")
    Account selectAccount(String email, String wachtwoord);

    Account getAccount(String email, String wachtwoord);
}
