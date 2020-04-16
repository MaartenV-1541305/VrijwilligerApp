package com.uhasselt.VrijwilligerApp.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inschrijving {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Account account;

    @OneToOne
    private Evenement evenement;

    private boolean aanwezigheid;

    @OneToMany
    private List<Benodigheid> benodigheden;

    @OneToOne
    private Taak taak;

    public Inschrijving() {
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

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public boolean isAanwezigheid() {
        return aanwezigheid;
    }

    public void setAanwezigheid(boolean aanwezigheid) {
        this.aanwezigheid = aanwezigheid;
    }

    public List<Benodigheid> getBenodigheden() {
        return benodigheden;
    }

    public void setBenodigheden(List<Benodigheid> benodigheden) {
        this.benodigheden = benodigheden;
    }

    public Taak getTaak() {
        return taak;
    }

    public void setTaak(Taak taak) {
        this.taak = taak;
    }
}