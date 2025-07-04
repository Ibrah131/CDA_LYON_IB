package org.example.model;

import org.example.interfaces.Paiement;

public class PayPal implements Paiement {
    private String email;
    private String motDePasse;
    private Compte compte;

    public PayPal(String email, String motDePasse, Compte compte) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.compte = compte;
    }

    @Override
    public String effectuerPaiement(double montant) {
        if (compte.getSolde() >= montant) {
            compte.debiter(montant);
            return "Paiement de " + montant + " € effectué avec succès via PayPal.";
        } else {
            return "Échec du paiement PayPal : solde insuffisant (" + compte.getSolde() + " €).";
        }
    }
}
