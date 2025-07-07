package org.example.model;


public abstract class Animal {
    protected String nom;
    protected int age;
    protected double poids;

    public Animal(String nom, int age, double poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
    }

    public abstract void manger();
    public abstract void dormir();
    public abstract void faireDuBruit();

    @Override
    public String toString() {
        return nom + " (" + age + " ans, " + poids + "kg)";
    }
}
