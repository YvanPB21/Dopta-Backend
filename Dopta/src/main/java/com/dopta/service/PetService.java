package com.dopta.service;

import com.dopta.dto.pet.CreatePetDTO;
import com.dopta.dto.pet.EditPetDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.Pet;
import com.dopta.model.Species;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet save(CreatePetDTO petDTO);
    Optional<PetDTO>findById(Integer id);
    List<PetDTO>listAllPets();
    Pet edit(EditPetDTO petDTO, Integer id);
    void deleteById(Integer id);
}