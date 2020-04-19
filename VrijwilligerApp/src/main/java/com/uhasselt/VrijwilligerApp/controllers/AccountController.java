package com.uhasselt.VrijwilligerApp.controllers;

import com.uhasselt.VrijwilligerApp.models.Account;
import com.uhasselt.VrijwilligerApp.models.Taak;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class AccountController {

    @CrossOrigin
    @ResponseBody
    @PostMapping(path = {"/account"})
    public ResponseEntity<Account> nieuwAccount(@RequestBody Account account) {
        //Parameters aanpassen.
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }
}
