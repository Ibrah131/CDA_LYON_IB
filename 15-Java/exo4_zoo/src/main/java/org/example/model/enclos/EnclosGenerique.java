package org.example.model.enclos;

import org.example.model.Animal;
import java.util.ArrayList;
import java.util.List;

public class EnclosGenerique<T extends Animal> {
    private final List<T> animaux = new ArrayList<>();
    private final Class<T> type;

    public EnclosGenerique(Class<T> type) {
        this.type = type;
    }

    public void ajouterAnimal(T animal) {
        animaux.add(animal);
    }

    public void afficherAnimaux() {
        if (animaux.isEmpty()) {
            System.out.println("Aucun animal dans cet enclos.");
        } else {
            for (T animal : animaux) {
                System.out.println(animal);
            }
        }
    }

    public List<T> getAnimaux() {
        return animaux;
    }

    public Class<T> getType() {
        return type;
    }
}
