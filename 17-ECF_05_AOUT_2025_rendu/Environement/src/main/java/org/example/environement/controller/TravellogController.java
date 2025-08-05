package org.example.environement.controller;

import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.service.TravellogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.environement.dto.travellogs.TravellogDtoReceive;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travellog")
public class TravellogController {

    private final TravellogService travellogsService;

    public TravellogController(TravellogService travellogsService) {
        this.travellogsService = travellogsService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTravellogs() {
        return ResponseEntity.ok(travellogsService.get());
    }


    @GetMapping("/stats/{id}")
    public ResponseEntity<TravellogDtoStat> getStatFromObseration (@PathVariable long id){
        return ResponseEntity.ok(travellogsService.getStat(id));
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<Map<String,TravellogDtoStat>> getTravelStatForUserOnLAstMonth (@PathVariable String name){
        return ResponseEntity.ok(travellogsService.getStatForUserLastMonth(name));
    }

    @PostMapping
    public ResponseEntity<TravellogDtoResponse> create(@RequestBody TravellogDtoReceive dto) {
        return ResponseEntity.status(201).body(travellogsService.create(dto));
    }

    @PostMapping("/{observationId}")
    public ResponseEntity<TravellogDtoResponse> createTravellog(
            @PathVariable long observationId,
            @RequestBody TravellogDtoReceive dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(travellogsService.create(dto, observationId));
    }


}
