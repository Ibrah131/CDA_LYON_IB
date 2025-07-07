package org.example.model.enclos;
import org.example.model.Animal;

public interface Enclos<T extends Animal> {
    void ajouterAnimal(T animal);
    void afficherAnimaux();
}

