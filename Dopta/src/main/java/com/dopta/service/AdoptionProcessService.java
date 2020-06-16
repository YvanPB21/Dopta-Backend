package com.dopta.service;

import com.dopta.dto.adoptionprocess.AdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.CreateAdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.EditAdoptionProcessDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.User;

import java.util.List;
import java.util.Optional;

public interface AdoptionProcessService {
    AdoptionProcess getAdoption(Integer id);
    AdoptionProcess save(CreateAdoptionProcessDTO adoptionProcessDTO);
    Optional<AdoptionProcessDTO> findById(Integer id);
    List<AdoptionProcessDTO> listAllAdoptions();
    AdoptionProcess edit(EditAdoptionProcessDTO adoptionProcessDTO, Integer id);
    List<AdoptionProcessDTO>findByPet(PetDTO pet);
    void deleteById(Integer id);
}