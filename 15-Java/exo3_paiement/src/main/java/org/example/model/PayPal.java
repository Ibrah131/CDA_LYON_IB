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
        if (compte.debiter(montant)) {
            return "Paiement de " + montant + "€ effectué via PayPal.";
        } else {
            return "Échec : solde insuffisant sur le compte PayPal.";
        }
    }
}
