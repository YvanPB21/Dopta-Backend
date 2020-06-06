package com.dopta.service;

import com.dopta.model.Pet;
import com.dopta.model.Species;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet getPet(Integer id);
    Pet save(Pet pet);
    Optional<Pet>findById(Integer id);
    List<Pet>listAllPets();
    Pet edit(Pet pet, Integer id);
    List<Pet>findBySpecies(Species species);
    void deleteById(Integer id);
}