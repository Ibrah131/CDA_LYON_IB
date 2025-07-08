package org.example;

import org.example.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Exemple de listes simulant la base de données
    private static final List<Client> clients = new ArrayList<>();
    private static final List<Evenement> evenements = new ArrayList<>();
    private static final List<Billet> billets = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Bienvenue dans le système de billetterie ===");

        // Ajout de données pour tester l'affichage
        seedData();

        boolean continuer = true;
        while (continuer) {
            System.out.println("\nMenu principal :");
            System.out.println("1. Gérer les Clients");
            System.out.println("2. Gérer les Événements");
            System.out.println("3. Gérer les Billets");
            System.out.println("0. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // nettoyer

            switch (choix) {
                case 1 -> menuClients();
                case 2 -> menuEvenements();
                case 3 -> menuBillets();
                case 0 -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }

        System.out.println("Au revoir !");
    }


    // ====================================================================== //
    // === CRUD Clients ===
    private static void menuClients() {
        System.out.println("\n--- Gestion des Clients ---");
        System.out.println("1. Afficher les clients");
        System.out.println("2. Ajouter un client (à faire)");
        System.out.println("3. Modifier un client (à faire)");
        System.out.println("4. Supprimer un client (à faire)");
        System.out.println("0. Retour");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> afficherClients();
            case 0 -> {}
            default -> System.out.println("Fonction non encore implémentée.");
        }
    }

    private static void afficherClients() {
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }

        for (int i = 0; i < clients.size(); i++) {
            System.out.println("[" + i + "] " + clients.get(i));
        }
    }

    // ====================================================================== //
    // === CRUD Événements ===
    private static void menuEvenements() {
        System.out.println("\n--- Gestion des Événements ---");
        System.out.println("1. Afficher les événements");
        System.out.println("0. Retour");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> afficherEvenements();
            case 0 -> {}
            default -> System.out.println("Fonction non encore implémentée.");
        }
    }

    private static void afficherEvenements() {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement disponible.");
            return;
        }

        for (int i = 0; i < evenements.size(); i++) {
            System.out.println("[" + i + "] " + evenements.get(i));
        }
    }

    // ====================================================================== //
    // === CRUD Billets ===
    private static void menuBillets() {
        System.out.println("\n--- Gestion des Billets ---");
        System.out.println("1. Afficher les billets");
        System.out.println("0. Retour");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> afficherBillets();
            case 0 -> {}
            default -> System.out.println("Fonction non encore implémentée.");
        }
    }

    private static void afficherBillets() {
        if (billets.isEmpty()) {
            System.out.println("Aucun billet généré.");
            return;
        }

        for (int i = 0; i < billets.size(); i++) {
            System.out.println("[" + i + "] " + billets.get(i));
        }
    }

    // ====================================================================== //
    // === Exemple de données (pour test affichage) ===
    private static void seedData() {
        Adresse a1 = new Adresse("10 rue de la paix", "Paris");
        Client c1 = new Client("Joe", "Loe", a1, 32, "0612345678");

        Adresse a2 = new Adresse("12 bd Voltaire", "Marseille");
        Client c2 = new Client("Doe", "Paul", a2, 28, "0623456789");

        clients.add(c1);
        clients.add(c2);

        Lieu lieu = new Lieu("15 rue Hugo", "Lyon", 3);
        Evenement e1 = new Evenement("Concert Metal", lieu, LocalDate.of(2025, 10, 5), null, 3);
        evenements.add(e1);

        Billet b1 = new Billet(1, c1, e1, TypePlace.VIP);
        Billet b2 = new Billet(2, c2, e1, TypePlace.STANDARD);

        billets.add(b1);
        billets.add(b2);
    }
}
