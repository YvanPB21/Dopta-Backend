package com.dopta.service;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdoptionProcessService {
    AdoptionProcess getAdoptionProcessById(Integer id);

    Page<AdoptionProcess> getAdoptionProcessByPetId(Integer petId, Pageable pageable);

    Page<AdoptionProcess> getAdoptionProcessByPosterId(Integer petId, Pageable pageable);

    Page<AdoptionProcess> getAdoptionProcessByPet(Pet pet, Pageable pageable);

    Page<AdoptionProcess> getAllAdoptionProcesses(Pageable pageable);

    AdoptionProcess createAdoptionProcess(AdoptionProcess adoptionProcess, Integer petId, Integer publisherId, Integer adopterId);

    AdoptionProcess editAdoptionProcess(AdoptionProcess adoptionProcessRequest, Integer adoptionProcessId, Integer petId, Integer posterId, Integer adopterId);

    ResponseEntity<?> deleteAdoptionProcessById(Integer adoptionProcessId);
}