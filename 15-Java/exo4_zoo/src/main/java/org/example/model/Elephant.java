package org.example.model;
public class Elephant extends Animal {
    public Elephant(String nom, int age, double poids) {
        super(nom, age, poids);
    }

    public void manger() { System.out.println(nom + " mange des plantes."); }
    public void dormir() { System.out.println(nom + " dort debout."); }
    public void faireDuBruit() { System.out.println(nom + " barrit !"); }
}
