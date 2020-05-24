package com.uhasselt.VrijwilligerApp.models;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private String voornaam;

    private String email;

    private String password;

    @OneToOne
    private Adres adres;

    public Account() {
    }

    public Account(String nm, String vnm, String email, String pw, Adres adres) {
        //TODO: ID?
        this.naam = vnm;
        this.voornaam = vnm;
        this.email = email;
        this.password = pw;
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
