package com.dopta.service;


import com.dopta.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PetService {
    Pet getPetById(Integer petId);

    Page<Pet> getPetsBySpeciesId(Integer speciesId, Pageable pageable);

    Page<Pet> getPetsBySexId(Integer sexId, Pageable pageable);

    Page<Pet> getPetsBySizeId(Integer sizeId, Pageable pageable);

    Page<Pet> getPetsBySpeciesIdAndSizeId(Integer speciesId, Integer sizeId, Pageable pageable);

    Page<Pet> getPetsBySpeciesIdAndSexId(Integer speciesId, Integer sexId, Pageable pageable);

    Page<Pet> getPetsBySizeIdAndSexId(Integer sizeId, Integer sexId, Pageable pageable);

    Page<Pet> getPetsBySpeciesIdAndSizeIdAndSexId(Integer speciesId, Integer sizeId, Integer sexId, Pageable pageable);

    Page<Pet> getAllPets(Pageable pageable);

    Pet createPet(Pet pet, Integer sizeId, Integer speciesId, Integer sexId);

    Pet editPet(Pet petRequest, Integer petId, Integer sizeId, Integer speciesId, Integer sexId);

    ResponseEntity<?> deletePetById(Integer petId);
}