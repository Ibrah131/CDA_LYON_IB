package org.example.app;

import org.example.interfaces.Paiement;
import org.example.model.CarteDeCredit;
import org.example.model.PayPal;

import java.util.Scanner;

public class GestionPaiements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Paiement paiement = null;

        int choix;
        do {
            System.out.println("\n=== Menu Paiement ===");
            System.out.println("1 - Paiement par carte de crédit");
            System.out.println("2 - Paiement via PayPal");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // retour à la ligne

            switch (choix) {
                case 1 -> {
                    System.out.print("Nom du titulaire : ");
                    String titulaire = scanner.nextLine();
                    System.out.print("Numéro de carte : ");
                    String numero = scanner.nextLine();
                    System.out.print("Date d'expiration (MM/AA) : ");
                    String date = scanner.nextLine();
                    System.out.print("Code CVV : ");
                    String cvv = scanner.nextLine();

                    paiement = new CarteDeCredit(numero, titulaire, date, cvv);
                }
                case 2 -> {
                    System.out.print("Email PayPal : ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String mdp = scanner.nextLine();

                    paiement = new PayPal(email, mdp);
                }
                case 0 -> System.out.println("Fin du programme.");
                default -> System.out.println("Choix invalide.");
            }

            if (paiement != null) {
                System.out.print("Montant du paiement : ");
                double montant = scanner.nextDouble();
                scanner.nextLine();
                System.out.println(paiement.effectuerPaiement(montant));
                paiement = null; // reset pour prochaine boucle
            }

        } while (choix != 0);
    }
}