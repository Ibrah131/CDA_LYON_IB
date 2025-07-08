package org.example.model;

public class Lieu extends Adresse {
    private int capacite;

    public Lieu(String rue, String ville, int capacite) {
        super(rue, ville);
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return super.toString() + " (Capacité: " + capacite + ")";
    }
}
