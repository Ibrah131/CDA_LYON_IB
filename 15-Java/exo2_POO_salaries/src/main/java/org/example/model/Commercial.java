package org.example.model;

public class Commercial extends Salarie {
    private double chiffreAffaire;
    private double commissionPourcentage;

    public Commercial(String matricule, String service, String categorie, String nom,
                      double salaire, double chiffreAffaire, double commissionPourcentage) {
        super(matricule, service, categorie, nom, salaire);
        this.chiffreAffaire = chiffreAffaire;
        this.commissionPourcentage = commissionPourcentage;
    }

    @Override
    public void afficherSalaire() {
        double salaireTotal = salaire + (chiffreAffaire * commissionPourcentage / 100);
        System.out.println("Le salaire de " + nom + " (Commercial) est de " + salaireTotal + " €");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Chiffre d'affaire: " + chiffreAffaire +
                ", Commission: " + commissionPourcentage + "%";
    }
}
