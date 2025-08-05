package org.example.environement.repository;

import org.example.environement.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    // Filtrer par espèce
    List<Observation> findBySpecie_Id(Long specieId);

    // Filtrer par lieu
    List<Observation> findByLocationContainingIgnoreCase(String location);
}
