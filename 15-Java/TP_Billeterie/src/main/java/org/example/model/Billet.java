package org.example.model;

public class Billet {
    private int numeroPlace;
    private Client client;
    private Evenement evenement;
    private TypePlace typePlace;

    public Billet(int numeroPlace, Client client, Evenement evenement, TypePlace typePlace) {
        this.numeroPlace = numeroPlace;
        this.client = client;
        this.evenement = evenement;
        this.typePlace = typePlace;
    }

    @Override
    public String toString() {
        return "Billet #" + numeroPlace + " - " + typePlace + " - " + client + " - Événement: " + evenement;
    }
}
