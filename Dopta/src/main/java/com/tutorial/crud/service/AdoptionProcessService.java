package com.tutorial.crud.service;

import com.tutorial.crud.entity.AdoptionProcess;
import com.tutorial.crud.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

public interface AdoptionProcessService {
    AdoptionProcess getAdoptionProcessById(Integer id);
    Page<AdoptionProcess> getAdoptionProcessByPetId(Integer petId, Pageable pageable);
    Page<AdoptionProcess> getAdoptionProcessByPet(Pet pet, Pageable pageable);
    Page<AdoptionProcess> getAllAdoptionProcesses(Pageable pageable);
    AdoptionProcess createAdoptionProcess(AdoptionProcess adoptionProcess, Integer petId, Integer publisherId, Integer adopterId);
    AdoptionProcess editAdoptionProcess(AdoptionProcess adoptionProcessRequest, Integer adoptionProcessId, Integer petId, Integer posterId, Integer adopterId);
    ResponseEntity<?> deleteAdoptionProcessById(Integer adoptionProcessId);
}