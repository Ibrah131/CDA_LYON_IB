package org.example.environement.service;

import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.entity.Travellog;
import org.example.environement.exception.NotFoundException;
import org.example.environement.repository.ObservationRepository;
import org.example.environement.repository.TravellogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;


@Service
public class TravellogService {

    private final TravellogRepository travellogRepository;
    private final ObservationRepository observationRepository;

    public TravellogService(TravellogRepository travellogRepository, ObservationRepository observationRepository) {
        this.travellogRepository = travellogRepository;
        this.observationRepository = observationRepository;
    }

    // Déplacement avec calcul CO2
    public TravellogDtoResponse create(TravellogDtoReceive dto) {
        Travellog entity = dto.dtoToEntity();
        entity.setObservation(
                observationRepository.findById(dto.getObservationId())
                        .orElseThrow(NotFoundException::new)
        );
        entity.calculateCO2();
        return travellogRepository.save(entity).entityToDto();
    }

    // Renvoyer tous les déplacements
    public Map<String, Object> get() {
        List<Travellog> logs = travellogRepository.findAll();

        double totalDistance = 0;
        double totalCo2 = 0;

        List<TravellogDtoResponse> responses = logs.stream().map(log -> {
            totalDistance += log.getDistanceKm();
            totalCo2 += log.getEstimatedCo2Kg();
            return log.entityToDto();
        }).toList();

        Map<String, Object> result = new HashMap<>();
        result.put("logs", responses);
        result.put("totalDistanceKm", totalDistance);
        result.put("totalEmissionsKg", totalCo2);

        return result;
    }


    // Statistiques CO2
    public TravellogDtoStat getStat(long observationId) {
        List<Travellog> logs = travellogRepository.findTravellogByObservation_Id(observationId);

        TravellogDtoStat stat = new TravellogDtoStat();

        for (Travellog log : logs) {
            stat.addTotalDistanceKm(log.getDistanceKm());
            stat.addTotalEmissionsKg(log.getEstimatedCo2Kg());
            stat.addMode(log.getMode().toString(), log.getDistanceKm());
        }

        return stat;
    }

    public TravellogDtoResponse create(TravellogDtoReceive dto, long observationId) {
        Observation observation = observationRepository.findById(observationId)
                .orElseThrow(NotFoundException::new);

        Travellog travellog = dto.dtoToEntity();
        travellog.setObservation(observation);

        Travellog saved = travellogRepository.save(travellog);
        return saved.entityToDto();
    }

}
