package com.uhasselt.VrijwilligerApp.models;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

//    @OneToMany
//    private List<GroepsLid> admins;

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

    public void addLid(GroepsLid lid) {
        this.leden.add(lid);
    }
    
    public List<GroepsLid> getAdmins() {
        List<GroepsLid> admins = new ArrayList<>();
        leden.stream().filter((groepsLid) -> (groepsLid.isAdmin())).forEachOrdered((groepsLid) -> {
            admins.add(groepsLid);
        });
        return admins;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.naam);
        hash = 83 * hash + Objects.hashCode(this.beschrijving);
        hash = 83 * hash + Objects.hashCode(this.leden);
        hash = 83 * hash + Objects.hashCode(this.maker);
        hash = 83 * hash + (this.verified ? 1 : 0);
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
        final Groep other = (Groep) obj;
        if (this.verified != other.verified) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Objects.equals(this.beschrijving, other.beschrijving)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
