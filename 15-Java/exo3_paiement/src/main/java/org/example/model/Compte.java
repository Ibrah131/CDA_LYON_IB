package org.example.model;

public class Compte {
    private double solde;

    public Compte(double soldeInitial) {
        this.solde = soldeInitial;
    }

    public double getSolde() {
        return solde;
    }

    public boolean debiter(double montant) {
        if (solde >= montant) {
            solde -= montant;
            return true;
        }
        return false;
    }

    public void crediter(double montant) {
        this.solde += montant;
    }

    @Override
    public String toString() {
        return "Solde du compte : " + solde + "€";
    }
}
