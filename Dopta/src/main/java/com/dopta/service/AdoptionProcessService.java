package com.dopta.service;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.User;

import java.util.List;
import java.util.Optional;

public interface AdoptionProcessService {
    AdoptionProcess getAdoption(Integer id);
    AdoptionProcess save(AdoptionProcess adoptionProcess);
    Optional<AdoptionProcess> findById(Integer id);
    List<AdoptionProcess> listAllAdoptions();
    AdoptionProcess edit(AdoptionProcess adoptionProcess, Integer id);
    List<AdoptionProcess>findByPet(Pet pet);
    void deleteById(Integer id);
}