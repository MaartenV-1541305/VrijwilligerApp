package com.uhasselt.VrijwilligerApp.models;

import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany
    private List<Benodigheid> benodigheden = new ArrayList<>();

    @OneToMany
    private List<Taak> taken = new ArrayList<>();

    @OneToMany
    private List<Account> inschrijvingen = new ArrayList<>();

    @OneToOne
    private Adres adres;

    @OneToOne
    private Account accountOrganisator;

    @OneToOne
    private Groep groepOrganisator;

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

    public void addTaak(Taak taak){
        taken.add(taak);
    }

    public void deleteTaak(Taak taak){taken.remove(taak);}

    public void addBenodigheid(Benodigheid benodigheid){
        benodigheden.add(benodigheid);
    }

    public void deleteBenodigheid(Benodigheid benodigheid){benodigheden.remove(benodigheid);}

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

    public Account getAccountOrganisator() {
        return accountOrganisator;
    }

    public void setAccountOrganisator(Account accountOrganisator) {
        this.accountOrganisator = accountOrganisator;
    }

    public Groep getGroepOrganisator() {
        return groepOrganisator;
    }

    public void setGroepOrganisator(Groep groepOrganisator) {
        this.groepOrganisator = groepOrganisator;
    }

    public boolean organisatorIsGroep(){
        return groepOrganisator != null;
    }
    public boolean organisatorIsAccount(){
        return accountOrganisator != null;
    }
}
