package org.example.app;

import org.example.interfaces.Paiement;
import org.example.model.*;

import java.util.Scanner;

public class GestionPaiements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Paiement paiement;

        System.out.println("=== Système de Paiement ===");
        System.out.println("1. Carte de Crédit");
        System.out.println("2. PayPal");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();

        System.out.print("Montant à payer : ");
        double montant = scanner.nextDouble();

        switch (choix) {
            case 1:
                Compte compteCarte = new Compte(100); // Exemple : 100€ dispo
                paiement = new CarteDeCredit("1234", "Jean", "12/25", "123", compteCarte);
                break;
            case 2:
                Compte comptePayPal = new Compte(50); // Exemple : 50€ dispo
                paiement = new PayPal("jean@email.com", "motdepasse", comptePayPal);
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        System.out.println(paiement.effectuerPaiement(montant));
    }
}
