package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.interfaces.IAccountService;
import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Adres;
import com.uhasselt.VrijwilligerApp.models.Taak;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class AccountController {
    private IAccountService service;

    public AccountController(IAccountService accountService){
        service = accountService;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/account"})
    public ResponseEntity<Account> nieuwAccount(String nm, String vnm, String email, String pw, String bevestPw, String adres) {
        if (checkPassed(nm, vnm, email, pw, bevestPw, adres)) {
            Account account = service.aanmakenAccount(nm, vnm, email, pw, bevestPw, adres);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            System.out.println("Wachtwoorden komen niet overeen!");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    private boolean checkPassed( String nm, String vnm, String email, String pw, String bevestPw, String adres){
        if(nm.isEmpty() || vnm.isEmpty() || email.isEmpty() || pw.isEmpty() || bevestPw.isEmpty() || adres.isEmpty() ){
            System.out.println("Er is een blank veld");
            return false;
        }
        if(!pw.equals(bevestPw)){
            return false;
        }
        return true;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/account/"})
    public ResponseEntity<Account> inloggen(String email, String wachtwoord){
        if( email.isEmpty() || !wachtwoord.isEmpty() ){
            Account account = service.inloggen(email, wachtwoord);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

    }
}
