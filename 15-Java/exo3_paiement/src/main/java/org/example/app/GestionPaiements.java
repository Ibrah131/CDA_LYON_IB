package org.example.app;

import org.example.interfaces.Paiement;
import org.example.model.CarteDeCredit;
import org.example.model.Compte;
import org.example.model.PayPal;

import java.util.Scanner;

public class GestionPaiements {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création des comptes
        Compte compteCarte = new Compte(200.0);
        Compte comptePaypal = new Compte(150.0);

        // Création des moyens de paiement
        Paiement carte = new CarteDeCredit("1234", "Ibrahim", "12/25", "123", compteCarte);
        Paiement paypal = new PayPal("ibrahim@mail.com", "motdepasse", comptePaypal);

        System.out.println("=== Système de Paiement ===");
        System.out.print("Montant à payer : ");
        double montant = scanner.nextDouble();

        System.out.println("1. Payer par Carte de Crédit");
        System.out.println("2. Payer par PayPal");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();

        String resultat;
        if (choix == 1) {
            resultat = carte.effectuerPaiement(montant);
        } else if (choix == 2) {
            resultat = paypal.effectuerPaiement(montant);
        } else {
            resultat = "Option invalide.";
        }

        System.out.println(resultat);
        scanner.close();
    }
}
