package org.example.environement.dto.travellogs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.entity.enums.TravelMode;
import org.example.environement.entity.Travellog;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TravellogDtoReceive {
    private double distanceKm;
    private String mode;
    private long observationId; // FK vers l'observation

    public Travellog dtoToEntity (){
        Travellog travellog = Travellog.builder()
                .distanceKm(this.getDistanceKm())
                .mode(TravelMode.valueOf(this.getMode()))
                .build();

        travellog.calculateCO2();
        return travellog;
    }
}
