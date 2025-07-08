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

    public void ajouterBillet(Billet billet) {
        billets.add(billet);
    }

    public boolean verifierPlacesDisponibles() {
        return billets.size() < nombrePlaces;
    }

    public int getPlacesRestantes() {
        return nombrePlaces - billets.size();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public List<Billet> getBillets() {
        return billets;
    }




    @Override
    public String toString() {
        return nom + " - " + lieu + " le " + date + (heure != null ? " à " + heure : "") + " (Places Restantes: " + getPlacesRestantes() + ")";
    }
}
