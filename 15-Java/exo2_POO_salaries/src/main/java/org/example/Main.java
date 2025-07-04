package org.example;

import org.example.model.Salarie;
import org.example.model.Commercial;

public class Main {
    public static void main(String[] args) {
        Salarie s1 = new Salarie("0001", "RH", "A", "Joe", 2500);
        Salarie s2 = new Salarie("0002", "IT", "B", "Roe", 3200);
        Salarie s3 = new Salarie("0003", "RH", "A", "Poe", 2400);

        Commercial c1 = new Commercial("0004", "Ventes", "C", "Zoe", 2000, 15000, 7.5);
        Commercial c2 = new Commercial("0005", "Ventes", "B", "Moe", 2100, 12000, 5.0);

        // Affichage des employés
        System.out.println("\n=== Salariés ===");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("\n=== Commerciaux ===");
        System.out.println(c1);
        System.out.println(c2);

        // Affichage des salaires
        System.out.println("\n=== Salaires ===");
        s1.afficherSalaire();
        s2.afficherSalaire();
        s3.afficherSalaire();
        c1.afficherSalaire();
        c2.afficherSalaire();

        // Total
        System.out.println();
        Salarie.afficherTotal();
    }
}
