package com.tutorial.crud.service.impl;

import com.tutorial.crud.entity.Species;
import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.repository.SpeciesRepository;
import com.tutorial.crud.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    @Autowired
    SpeciesRepository speciesRepository;
    
    @Override
    public Species getSpeciesById(Integer speciesId) {
        return speciesRepository.findById(speciesId).orElseThrow(()->new ResourceNotFoundException("Species","Id",speciesId));
    }

    @Override
    public Page<Species> getAllSpecies(Integer speciesId, Pageable pageable) {
        return speciesRepository.findAll(pageable);
    }

    @Override
    public Species createSpecies(Species species) {
        Species newSpecies=new Species();
        newSpecies.setName(species.getName());
        return speciesRepository.save(newSpecies);
    }

    @Override
    public Species editSpecies(Species speciesRequest, Integer speciesId) {
        if(!speciesRepository.existsById(speciesId))
            throw new ResourceNotFoundException("Species","Id",speciesId);
        return speciesRepository.findById(speciesId).map(species->
        {
            species.setName(speciesRequest.getName());
            return speciesRepository.save(species);
        }).orElseThrow(()->new ResourceNotFoundException("Species","Id",speciesId));
    }

    @Override
    public ResponseEntity<?> deleteSpeciesById(Integer speciesId) {
        return speciesRepository.findById(speciesId).map(species->{
            speciesRepository.delete(species);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Species"));
    }
}
