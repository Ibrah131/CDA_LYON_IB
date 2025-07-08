package org.example.app;

import org.example.model.*;
import org.example.model.enclos.EnclosGenerique;

import java.util.*;

public class Zoo {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, EnclosGenerique<? extends Animal>> enclosMap = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            afficherMenu();
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> creerAnimal();
                case "2" -> creerEnclos();
                case "3" -> ajouterAnimalDansEnclos();
                case "4" -> afficherEnclos();
                case "0" -> {
                    System.out.println("À bientôt");
                    return;
                }
                default -> System.out.println("Choix invalide");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n--- Menu Zoo ---");
        System.out.println("1. Créer un animal");
        System.out.println("2. Créer un enclos");
        System.out.println("3. Ajouter un animal à un enclos");
        System.out.println("4. Afficher les animaux d'un enclos");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }

    private static void creerAnimal() {
        String type = demanderTypeAnimal();
        if (type == null) return;

        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Âge : ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Poids : ");
        double poids = Double.parseDouble(scanner.nextLine());

        Animal animal = switch (type) {
            case "Lion" -> new Lion(nom, age, poids);
            case "Elephant" -> new Elephant(nom, age, poids);
            default -> null;
        };

        if (animal == null) return;

        EnclosGenerique<? extends Animal> enclos = enclosMap.get(type);
        if (enclos != null) {
            ((EnclosGenerique<Animal>) enclos).ajouterAnimal(animal);
            System.out.println("Animal ajouté dans l'enclos : " + animal);
        } else {
            System.out.println("Enclos non trouvé. Créez l'enclos d'abord.");
        }
    }

    private static void creerEnclos() {
        String type = demanderTypeAnimal();
        if (type == null) return;

        if (enclosMap.containsKey(type)) {
            System.out.println("Enclos déjà existant.");
            return;
        }





        switch (type) {
            case "Lion" ->  enclosMap.put("Lion", new EnclosGenerique<>(Lion.class));
            case "Elephant" -> enclosMap.put("Elephant", new EnclosGenerique<>(Elephant.class));

        }

        System.out.println("Enclos pour " + type + " créé.");
    }

    private static void ajouterAnimalDansEnclos() {
        String type = demanderTypeAnimal();
        if (type == null) return;

        EnclosGenerique<? extends Animal> enclos = enclosMap.get(type);
        if (enclos == null) {
            System.out.println("⚠Aucun enclos trouvé pour ce type.");
            return;
        }

        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Âge : ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Poids : ");
        double poids = Double.parseDouble(scanner.nextLine());

        Animal animal = switch (type) {
            case "Lion" -> new Lion(nom, age, poids);
            case "Elephant" -> new Elephant(nom, age, poids);
            default -> null;
        };

        if (animal != null) {
            ((EnclosGenerique<Animal>) enclos).ajouterAnimal(animal);
            System.out.println("Animal ajouté : " + animal);
        }
    }

    private static void afficherEnclos() {
        String type = demanderTypeAnimal();
        if (type == null) return;

        EnclosGenerique<? extends Animal> enclos = enclosMap.get(type);
        if (enclos == null) {
            System.out.println("Aucun enclos trouvé.");
        } else {
            enclos.afficherAnimaux();
        }
    }

    private static String demanderTypeAnimal() {
        System.out.println("1. Lion");
        System.out.println("2. Elephant");
        System.out.print("Choix du type : ");
        String choix = scanner.nextLine();

        return switch (choix) {
            case "1" -> "Lion";
            case "2" -> "Elephant";
            default -> {
                System.out.println("Choix invalide.");
                yield null;
            }
        };
    }
}
