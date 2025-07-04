package org.example;

import org.example.model.Salarie;
import org.example.model.Commercial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Salarie> employes = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Salarie s1 = new Salarie("0001", "RH", "A", "Joe", 2500);
        Salarie s2 = new Salarie("0002", "IT", "B", "Roe", 3200);
        Salarie s3 = new Salarie("0003", "RH", "A", "Poe", 2400);

        Commercial c1 = new Commercial("0004", "Ventes", "C", "Zoe", 2000, 15000, 7.5);
        Commercial c2 = new Commercial("0005", "Ventes", "B", "Moe", 2100, 12000, 5.0);

        // Ajout à la liste de l'IHM
        employes.add(s1);
        employes.add(s2);
        employes.add(s3);
        employes.add(c1);
        employes.add(c2);



        int choix;

        do {
            System.out.println("\n=== Gestion des employés ===");
            System.out.println("1- Ajouter un employé");
            System.out.println("2- Afficher les salaires des employés");
            System.out.println("3- Rechercher un employé");
            System.out.println("4- Supprimer un employé");
            System.out.println("5- Quitter");
            System.out.print("Entrez votre choix : ");
            choix = lireInt();

            switch (choix) {
                case 1 -> ajouterEmploye();
                case 2 -> afficherSalaires();
                case 3 -> rechercherEmploye();
                case 4 -> supprimerEmploye();
                case 5 -> System.out.println("Fin du programme.");
                default -> System.out.println("Choix invalide.");
            }

        } while (choix != 5);
    }

    static void ajouterEmploye() {
        System.out.println("\n== Ajouter un employé ==");
        System.out.println("1- Salarié");
        System.out.println("2- Commercial");
        System.out.println("0- Retour");
        System.out.print("Votre choix : ");
        int type = lireInt();
        if (type == 0) return;

        System.out.print("Nom (0 pour annuler) : ");
        String nom = scanner.nextLine();
        if (nom.equals("0")) return;

        System.out.print("Matricule : ");
        String mat = scanner.nextLine();
        if (mat.equals("0")) return;

        System.out.print("Service : ");
        String service = scanner.nextLine();
        if (service.equals("0")) return;

        System.out.print("Catégorie : ");
        String cat = scanner.nextLine();
        if (cat.equals("0")) return;

        System.out.print("Salaire : ");
        double salaire = lireDouble();
        if (Double.isNaN(salaire)) return;

        if (type == 1) {
            employes.add(new Salarie(mat, service, cat, nom, salaire));
            System.out.println("Salarié ajouté !");
        } else if (type == 2) {
            System.out.print("Chiffre d'affaire : ");
            double ca = lireDouble();
            if (Double.isNaN(ca)) return;

            System.out.print("Commission (%) : ");
            double com = lireDouble();
            if (Double.isNaN(com)) return;

            employes.add(new Commercial(mat, service, cat, nom, salaire, ca, com));
            System.out.println("Commercial ajouté !");
        }
    }

    static void afficherSalaires() {
        System.out.println("\n== Salaires des employés ==");
        if (employes.isEmpty()) {
            System.out.println("Aucun employé.");
        } else {
            for (Salarie s : employes) {
                s.afficherSalaire();
            }
        }
    }

    static void rechercherEmploye() {
        System.out.print("\nEntrez le début du nom à rechercher (0 pour retour) : ");
        String prefix = scanner.nextLine();
        if (prefix.equals("0")) return;

        boolean trouve = false;
        for (Salarie s : employes) {
            if (s.getNom().toLowerCase().startsWith(prefix.toLowerCase())) {
                System.out.println(s);
                s.afficherSalaire();
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("Aucun employé trouvé.");
        }
    }

    static void supprimerEmploye() {
        System.out.print("\nEntrez un nom pour rechercher à supprimer (0 pour retour) : ");
        String saisie = scanner.nextLine();
        if (saisie.equals("0")) return;

        Iterator<Salarie> it = employes.iterator();
        boolean supprime = false;

        while (it.hasNext()) {
            Salarie s = it.next();
            if (s.getNom().toLowerCase().contains(saisie.toLowerCase())) {
                System.out.println("Supprimer : " + s + " ? (1 = oui / 0 = non)");
                int rep = lireInt();
                if (rep == 1) {
                    it.remove();
                    System.out.println("Employé supprimé.");
                    supprime = true;
                }
            }
        }

        if (!supprime) {
            System.out.println("Aucun employé supprimé.");
        }
    }

    static int lireInt() {
        try {
            String saisie = scanner.nextLine();
            return Integer.parseInt(saisie);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double lireDouble() {
        try {
            String saisie = scanner.nextLine();
            if (saisie.equals("0")) return Double.NaN;
            return Double.parseDouble(saisie);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}
