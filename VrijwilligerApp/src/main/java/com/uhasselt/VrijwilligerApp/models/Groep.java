package com.uhasselt.VrijwilligerApp.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Entity
public class Groep {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String beschrijving;

    private List<GroepsLid> leden;

    private List<GroepsLid> admins;

    private GroepsLid maker;

    private boolean verified;

    public Groep() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.maker = maker;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
