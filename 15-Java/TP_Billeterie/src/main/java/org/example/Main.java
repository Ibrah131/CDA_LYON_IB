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
            System.out.println("2. Gérer les Evénements");
            System.out.println("3. Gérer les Billets");
            System.out.println("0. Quitter");

            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

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
        System.out.println("2. Ajouter un client");
        System.out.println("3. Modifier un client");
        System.out.println("4. Supprimer un client");
        System.out.println("0. Retour");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> afficherClients();
            case 2 -> ajouterClient();
            case 3 -> modifierClient();
            case 4 -> supprimerClient();
            case 0 -> {}
            default -> System.out.println("Choix invalide.");
        }
    }

    // Afficher
    private static void afficherClients() {
        if (clients.isEmpty()) {
            System.out.println("Aucun client enregistré.");
            return;
        }

        for (int i = 0; i < clients.size(); i++) {
            System.out.println("[" + i + "] " + clients.get(i));
        }
    }

    // Ajouter
    private static void ajouterClient() {
        System.out.println("Nom :");
        String nom = scanner.nextLine();

        System.out.println("Prénom :");
        String prenom = scanner.nextLine();

        System.out.println("Âge :");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Téléphone :");
        String tel = scanner.nextLine();

        System.out.println("Rue :");
        String rue = scanner.nextLine();

        System.out.println("Ville :");
        String ville = scanner.nextLine();

        Adresse adresse = new Adresse(rue, ville);
        Client client = new Client(nom, prenom, adresse, age, tel);
        clients.add(client);

        System.out.println("Client ajouté avec succès !");
    }

    // Modifier
    private static void modifierClient() {
        afficherClients();

        System.out.println("Entrez l'index du client à modifier :");
        int index = scanner.nextInt();
        scanner.nextLine();

        try {
            if (index < 0 || index >= clients.size()) {
                throw new NotFoundException("Index client invalide.");
            }

            Client c = clients.get(index);

            System.out.println("Nouveau nom (" + c.getNom() + ") :");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) c.setNom(nom);

            System.out.println("Nouveau prénom (" + c.getPrenom() + ") :");
            String prenom = scanner.nextLine();
            if (!prenom.isEmpty()) c.setPrenom(prenom);

            System.out.println("Nouvel âge (" + c.getAge() + ") :");
            String ageStr = scanner.nextLine();
            if (!ageStr.isEmpty()) c.setAge(Integer.parseInt(ageStr));

            System.out.println("Nouveau téléphone (" + c.getTelephone() + ") :");
            String tel = scanner.nextLine();
            if (!tel.isEmpty()) c.setTelephone(tel);

            System.out.println("Client modifié avec succès !");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Supprimer
    private static void supprimerClient() {
        afficherClients();

        System.out.println("Entrez l'index du client à supprimer :");
        int index = scanner.nextInt();
        scanner.nextLine();

        try {
            if (index < 0 || index >= clients.size()) {
                throw new NotFoundException("Client non trouvé.");
            }

            clients.remove(index);
            System.out.println("Client supprimé.");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    // ====================================================================== //
    // === CRUD Evénements ===
    private static void menuEvenements() {
        System.out.println("\n--- Gestion des Evénements ---");
        System.out.println("1. Afficher les événements");
        System.out.println("2. Ajouter un événement");
        System.out.println("3. Modifier un événement");
        System.out.println("4. Supprimer un événement");
        System.out.println("0. Retour");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> afficherEvenements();
            case 2 -> ajouterEvenement();
            case 3 -> modifierEvenement();
            case 4 -> supprimerEvenement();
            case 0 -> {}
            default -> System.out.println("Choix invalide.");
        }
    }

    // Afficher
    private static void afficherEvenements() {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement disponible.");
            return;
        }

        for (int i = 0; i < evenements.size(); i++) {
            System.out.println("[" + i + "] " + evenements.get(i));
        }
    }

    // Ajouter
    private static void ajouterEvenement() {
        System.out.println("Nom de l'événement :");
        String nom = scanner.nextLine();

        System.out.println("Rue du lieu :");
        String rue = scanner.nextLine();
        System.out.println("Ville du lieu :");
        String ville = scanner.nextLine();

        System.out.println("Capacité du lieu :");
        int capacite = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nombre total de places :");
        int nbPlaces = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Date (AAAA-MM-JJ) :");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        Lieu lieu = new Lieu(rue, ville, capacite);
        Evenement evenement = new Evenement(nom, lieu, date, null, nbPlaces);
        evenements.add(evenement);

        System.out.println("Événement ajouté avec succès !");
    }

    // Modifier
    private static void modifierEvenement() {
        afficherEvenements();
        System.out.println("Index de l'événement à modifier :");
        int index = scanner.nextInt();
        scanner.nextLine();

        try {
            if (index < 0 || index >= evenements.size()) {
                throw new NotFoundException("Evénement introuvable.");
            }

            Evenement e = evenements.get(index);

            System.out.println("Nouveau nom (" + e.getNom() + ") :");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) e.setNom(nom);

            System.out.println("Nouvelle date (" + e.getDate() + ") (AAAA-MM-JJ) :");
            String dateStr = scanner.nextLine();
            if (!dateStr.isEmpty()) e.setDate(LocalDate.parse(dateStr));

            System.out.println("Nombre total de places (" + e.getNombrePlaces() + ") :");
            String nbStr = scanner.nextLine();
            if (!nbStr.isEmpty()) e.setNombrePlaces(Integer.parseInt(nbStr));

            System.out.println("Evénement modifié !");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Supprimer
    private static void supprimerEvenement() {
        afficherEvenements();
        System.out.println("Index de l'événement à supprimer :");
        int index = scanner.nextInt();
        scanner.nextLine();

        try {
            if (index < 0 || index >= evenements.size()) {
                throw new NotFoundException("Evénement introuvable.");
            }

            evenements.remove(index);
            System.out.println("Evénement supprimé.");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // ====================================================================== //
    // === CRUD Billets ===
    private static void menuBillets() {
        System.out.println("\n--- Gestion des Billets ---");
        System.out.println("1. Afficher les billets");
        System.out.println("2. Réserver un billet");
        System.out.println("3. Supprimer un billet");
        System.out.println("0. Retour");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1 -> afficherBillets();
            case 2 -> reserverBillet();
            case 3 -> supprimerBillet();
            case 0 -> {}
            default -> System.out.println("Choix invalide.");
        }
    }

    // Afficher
    private static void afficherBillets() {
        if (billets.isEmpty()) {
            System.out.println("Aucun billet généré.");
            return;
        }

        for (int i = 0; i < billets.size(); i++) {
            System.out.println("[" + i + "] " + billets.get(i));
        }
    }

    // Réserver
    private static void reserverBillet() {
        if (clients.isEmpty() || evenements.isEmpty()) {
            System.out.println("Il faut au moins un client et un événement pour réserver.");
            return;
        }

        try {
            System.out.println("Choisissez un client :");
            afficherClients();
            int clientIndex = scanner.nextInt();
            scanner.nextLine();

            if (clientIndex < 0 || clientIndex >= clients.size()) {
                throw new NotFoundException("Client introuvable.");
            }

            System.out.println("Choisissez un événement :");
            afficherEvenements();
            int eventIndex = scanner.nextInt();
            scanner.nextLine();

            if (eventIndex < 0 || eventIndex >= evenements.size()) {
                throw new NotFoundException("Evénement introuvable.");
            }

            Evenement event = evenements.get(eventIndex);
            if (!event.verifierPlacesDisponibles()) {
                System.out.println("Plus de places disponibles.");
                return;
            }

            System.out.println("Type de place (STANDARD, GOLD, VIP) :");
            String typeStr = scanner.nextLine().toUpperCase();
            TypePlace type = TypePlace.valueOf(typeStr);

            int numeroPlace = billets.size() + 1;

            Client client = clients.get(clientIndex);
            Billet billet = new Billet(numeroPlace, client, event, type);

            billets.add(billet);
            event.ajouterBillet(billet);
            client.ajouterBillet(billet);

            System.out.println("Billet réservé !");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Supprimer
    private static void supprimerBillet() {
        afficherBillets();
        System.out.println("Index du billet à supprimer :");
        int index = scanner.nextInt();
        scanner.nextLine();

        try {
            if (index < 0 || index >= billets.size()) {
                throw new NotFoundException("Billet introuvable.");
            }

            Billet billet = billets.remove(index);
            billet.getClient().getBillets().remove(billet);
            billet.getEvenement().getBillets().remove(billet);

            System.out.println("Billet supprimé.");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
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

        c1.ajouterBillet(b1);
        c2.ajouterBillet(b2);

        e1.ajouterBillet(b1);
        e1.ajouterBillet(b2);


    }
}
