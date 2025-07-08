package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nom;
    private String prenom;
    private Adresse adresse;
    private int age;
    private String telephone;
    private List<Billet> billets;

    public Client(String nom, String prenom, Adresse adresse, int age, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.age = age;
        this.telephone = telephone;
        this.billets = new ArrayList<>();
    }

    public void ajouterBillet(Billet billet) {
        billets.add(billet);
    }

    public List<Billet> getBillets() {
        return billets;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + age + " ans) - " + adresse;
    }
}
