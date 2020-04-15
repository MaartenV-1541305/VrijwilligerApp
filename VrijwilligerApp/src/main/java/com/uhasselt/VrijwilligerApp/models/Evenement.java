package com.uhasselt.VrijwilligerApp.models;

import com.uhasselt.VrijwilligerApp.interfaces.IOrganisator;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evenementen")
public class Evenement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private String beschrijving;

    private List<Benodigheid> benodigheden;

    private List<Taak> taken;

    private List<Account> inschrijvingen;

    private Adres adres;

    private IOrganisator organisator;

    public Evenement() {
    }

    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public List<Benodigheid> getBenodigheden() {
        return benodigheden;
    }

    public void setBenodigheden(List<Benodigheid> benodigheden) {
        this.benodigheden = benodigheden;
    }

    public List<Taak> getTaken() {
        return taken;
    }

    public void setTaken(List<Taak> taken) {
        this.taken = taken;
    }

    public List<Account> getInschrijvingen() {
        return inschrijvingen;
    }

    public void setInschrijvingen(List<Account> inschrijvingen) {
        this.inschrijvingen = inschrijvingen;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public IOrganisator getOrganisator() {
        return organisator;
    }

    public void setOrganisator(IOrganisator organisator) {
        this.organisator = organisator;
    }
}
