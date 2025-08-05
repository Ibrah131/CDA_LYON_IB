package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.entity.enums.TravelMode;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Travellog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double distanceKm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TravelMode mode;

    @Column(nullable = false)
    private double estimatedCo2Kg;

    @ManyToOne
    @JoinColumn(name = "observation_id", nullable = false)
    private Observation observation;

    // Calcul du CO2 selon mode
    public void calculateCO2() {
        double factor;
        switch (this.mode) {
            case WALKING, BIKE -> factor = 0;
            case CAR -> factor = 0.22;
            case BUS -> factor = 0.11;
            case TRAIN -> factor = 0.03;
            case PLANE -> factor = 0.259;
            default -> factor = 0;
        }
        this.estimatedCo2Kg = this.distanceKm * factor;
    }

    // Conversion vers DTO de réponse
    public TravellogDtoResponse entityToDto() {
        return TravellogDtoResponse.builder()
                .id(this.id)
                .distanceKm(this.distanceKm)
                .mode(this.mode.toString())
                .estimatedCo2Kg(this.estimatedCo2Kg)
                .build();
    }
}
