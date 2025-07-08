package org.example.model;

public class Billet {
    private int numeroPlace;
    private Client client;
    private Evenement evenement;
    private TypePlace type;

    public Billet(int numeroPlace, Client client, Evenement evenement, TypePlace type) {
        this.numeroPlace = numeroPlace;
        this.client = client;
        this.evenement = evenement;
        this.type = type;
    }

    public int getNumeroPlace() {
        return numeroPlace;
    }

    public Client getClient() {
        return client;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public TypePlace getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Billet #" + numeroPlace + " - " + type + " - " + client + " - Événement: " + evenement;
    }
}
