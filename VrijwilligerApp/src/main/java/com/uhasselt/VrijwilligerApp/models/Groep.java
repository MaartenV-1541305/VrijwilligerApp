package com.uhasselt.VrijwilligerApp.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groep {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private String beschrijving;

    @OneToMany
    private List<GroepsLid> leden;

    @OneToMany
    private List<GroepsLid> admins;

    @OneToOne
    private GroepsLid maker;

    private boolean verified;

    public Groep() { 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String name) {
        this.naam = name;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public List<GroepsLid> getLeden() {
        return leden;
    }

    public void setLeden(List<GroepsLid> leden) {
        this.leden = leden;
    }

    public List<GroepsLid> getAdmins() {
        return admins;
    }

    public void setAdmins(List<GroepsLid> admins) {
        this.admins = admins;
    }

    public GroepsLid getMaker() {
        return maker;
    }

    public void setMaker(GroepsLid maker) {
        maker.setAdmin(true);
        this.maker = maker;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
