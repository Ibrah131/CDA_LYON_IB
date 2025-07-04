package org.example.model;

import org.example.interfaces.Paiement;

public class CarteDeCredit implements Paiement {
    private String numeroCarte;
    private String titulaire;
    private String dateExpiration;
    private String codeCVV;
    private Compte compte;

    public CarteDeCredit(String numeroCarte, String titulaire, String dateExpiration, String codeCVV, Compte compte) {
        this.numeroCarte = numeroCarte;
        this.titulaire = titulaire;
        this.dateExpiration = dateExpiration;
        this.codeCVV = codeCVV;
        this.compte = compte;
    }

    @Override
    public String effectuerPaiement(double montant) {
        if (compte.debiter(montant)) {
            return "Paiement de " + montant + "€ effectué par Carte de Crédit.";
        } else {
            return "Échec : solde insuffisant sur la carte.";
        }
    }
}
