package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String name = demanderNom(scanner);
                int age = demanderAge(scanner);
                students.add(new Student(name, age));
                System.out.println("Étudiant ajouté avec succès !");
            } catch (InvalidNameException | InvalidAgeException e) {
                System.out.println("Erreur : " + e.getMessage());
            }

            System.out.print("Voulez-vous ajouter un autre étudiant ? (oui/non) : ");
            String reponse = scanner.nextLine();
            if (!reponse.equalsIgnoreCase("oui")) {
                break;
            }
        }

        afficherEtudiants();
    }

    private static String demanderNom(Scanner scanner) {
        System.out.print("Entrez le nom de l'étudiant : ");
        String name = scanner.nextLine();

        try {
            Integer.parseInt(name);
            throw new InvalidNameException("Le nom ne peut pas être un nombre.");
        } catch (NumberFormatException e) {
            // ok, continue
        }

        if (name.trim().isEmpty()) {
            throw new InvalidNameException("Le nom ne peut pas être vide.");
        }

        return name;
    }

    private static int demanderAge(Scanner scanner) {
        System.out.print("Entrez l'âge de l'étudiant : ");
        String input = scanner.nextLine();

        try {
            int age = Integer.parseInt(input);
            if (age < 0) {
                throw new InvalidAgeException("L'âge ne peut pas être négatif.");
            }
            return age;
        } catch (NumberFormatException e) {
            throw new InvalidAgeException("L'âge doit être un entier valide.");
        }
    }

    private static void afficherEtudiants() {
        System.out.println("\nListe des étudiants :");
        for (Student s : students) {
            System.out.println("- " + s);
        }
    }
}
