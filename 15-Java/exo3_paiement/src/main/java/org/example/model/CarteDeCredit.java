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
        if (compte.getSolde() >= montant) {
            compte.debiter(montant);
            return "Paiement de " + montant + " € effectué avec succès par Carte de Crédit.";
        } else {
            return "Échec du paiement par carte : solde insuffisant (" + compte.getSolde() + " €).";
        }
    }
}
