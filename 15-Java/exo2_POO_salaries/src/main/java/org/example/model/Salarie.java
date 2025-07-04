package org.example.model;

public class Salarie {
    private String matricule;
    private String service;
    private String categorie;
    protected String nom;
    protected double salaire;

    private static int nbEmployes = 0;
    private static double totalSalaires = 0;

    public Salarie(String matricule, String service, String categorie, String nom, double salaire) {
        this.matricule = matricule;
        this.service = service;
        this.categorie = categorie;
        this.nom = nom;
        this.salaire = salaire;

        nbEmployes++;
        totalSalaires += salaire;
    }

    public void afficherSalaire() {
        System.out.println("Le salaire de " + nom + " est de " + salaire + " €");
    }

    public static void afficherTotal() {
        System.out.println("Le montant total des salaires des " + nbEmployes + " employés est de " + totalSalaires + " €");
        System.out.println("Il y a " + nbEmployes + " salariés");
    }

    public static void resetTotal() {
        nbEmployes = 0;
        totalSalaires = 0;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Nom: " + nom +
                ", Matricule: " + matricule +
                ", Service: " + service +
                ", Catégorie: " + categorie +
                ", Salaire: " + salaire + " €";
    }
}
