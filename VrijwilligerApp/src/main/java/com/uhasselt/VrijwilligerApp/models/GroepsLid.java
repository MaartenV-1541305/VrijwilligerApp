package com.uhasselt.VrijwilligerApp.models;

import javax.persistence.*;

@Entity
public class GroepsLid {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Account account;
    
    @OneToOne
    private Groep groep;

    private boolean  isAdmin;
    
    public GroepsLid() {
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Groep getGroep() {
        return groep;
    }

    public void setGroep(Groep groep) {
        this.groep = groep;
    }
    
    
}
