package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Evenement {
    private String nom;
    private Lieu lieu;
    private LocalDate date;
    private LocalTime heure;
    private int nombrePlaces;
    private List<Billet> billets;

    public Evenement(String nom, Lieu lieu, LocalDate date, LocalTime heure, int nombrePlaces) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.heure = heure;
        this.nombrePlaces = nombrePlaces;
        this.billets = new ArrayList<>();
    }

    public boolean verifierPlacesDisponibles() {
        return billets.size() < nombrePlaces;
    }

    public void ajouterBillet(Billet billet) {
        billets.add(billet);
    }

    public List<Billet> getBillets() {
        return billets;
    }

    @Override
    public String toString() {
        return nom + " à " + lieu + " le " + date + " à " + heure + " (" + billets.size() + "/" + nombrePlaces + " réservées)";
    }
}
