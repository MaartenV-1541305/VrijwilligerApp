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

    @Query("INSERT INTO account (email, pasword, naam, voornaam, stad)" +
            "VALUES (%:email%, %:ww%, %:nm%, %:vnm%, %:stad%)")
    Account insertAccount(String email, String ww, String bev_ww, String nm, String vnm, String stad);


    Account inloggen(String email, String password);
    Account aanmakenAccount(String email, String ww, String bevest_ww, String nm, String vnm, String stad );
}
