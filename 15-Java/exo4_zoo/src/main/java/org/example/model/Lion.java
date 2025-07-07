package org.example.model;

public class Lion extends Animal {
    public Lion(String nom, int age, double poids) {
        super(nom, age, poids);
    }

    public void manger() { System.out.println(nom + " mange de la viande."); }
    public void dormir() { System.out.println(nom + " dort sous un arbre."); }
    public void faireDuBruit() { System.out.println(nom + " rugit !"); }
}
