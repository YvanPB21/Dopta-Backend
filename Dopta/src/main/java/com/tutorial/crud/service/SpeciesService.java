package com.tutorial.crud.service;

import com.tutorial.crud.model.Species;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SpeciesService {
    Species getSpeciesById(Integer speciesId);
    Page<Species> getAllSpecies(Pageable pageable);
    Species createSpecies(Species species);
    Species editSpecies(Species speciesRequest, Integer speciesId);
    ResponseEntity<?> deleteSpeciesById(Integer speciesId);
}
