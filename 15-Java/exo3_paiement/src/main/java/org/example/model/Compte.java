package org.example.model;

public class Compte {
    private double solde;

    public Compte(double solde) {
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }

    public void debiter(double montant) {
        if (montant <= solde) {
            solde -= montant;
        }
    }
}
