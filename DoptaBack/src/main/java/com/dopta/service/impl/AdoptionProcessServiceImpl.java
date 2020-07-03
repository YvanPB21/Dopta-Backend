package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import com.dopta.repository.AdoptionProcessRepository;
import com.dopta.repository.PetRepository;
import com.dopta.repository.UserRepository;
import com.dopta.service.AdoptionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdoptionProcessServiceImpl implements AdoptionProcessService {

    @Autowired
    private AdoptionProcessRepository adoptionProcessRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AdoptionProcess getAdoptionProcessById(Integer id) {
        return adoptionProcessRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Adoption Process", "Id", id));
    }

    @Override
    public Page<AdoptionProcess> getAdoptionProcessByPetId(Integer petId, Pageable pageable) {
        return adoptionProcessRepository.findAllByPetId(petId, pageable);
    }

    @Override
    public Page<AdoptionProcess> getAdoptionProcessByPet(Pet pet, Pageable pageable) {
        return adoptionProcessRepository.findAllByPet(pet, pageable);
    }

    @Override
    public Page<AdoptionProcess> getAllAdoptionProcesses(Pageable pageable) {
        return adoptionProcessRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public AdoptionProcess createAdoptionProcess(AdoptionProcess adoptionProcess, Integer petId, Integer adopterId, Integer posterId) {
        AdoptionProcess newProcess = new AdoptionProcess();
        newProcess.setPet(petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet", "Id", petId)));
        newProcess.setDate_published(adoptionProcess.getDate_published());
        newProcess.setDate_adopted(adoptionProcess.getDate_adopted());
        newProcess.setDescription(adoptionProcess.getDescription());
        newProcess.setAdopter(userRepository.findById(adopterId).orElseThrow(() -> new ResourceNotFoundException("Adopter", "Id", adopterId)));
        newProcess.setPoster(userRepository.findById(posterId).orElseThrow(() -> new ResourceNotFoundException("Poster", "Id", posterId)));

        return adoptionProcessRepository.save(newProcess);
    }

    /*TODO revisar*/

    @Override
    @Transactional
    public AdoptionProcess editAdoptionProcess(AdoptionProcess adoptionProcessRequest, Integer adoptionProcessId, Integer petId, Integer posterId, Integer adopterId) {
        if (!petRepository.existsById(petId))
            throw new ResourceNotFoundException("Pet", "Id", petId);
        if (!userRepository.existsById(posterId))
            throw new ResourceNotFoundException("Poster", "Id", posterId);
        if (!userRepository.existsById(adopterId))
            throw new ResourceNotFoundException("Adopter", "Id", adopterId);
        return adoptionProcessRepository.findById(adoptionProcessId).map(ap -> {
            ap.setPet(petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet", "Id", petId)));
            ap.setDate_published(adoptionProcessRequest.getDate_published());
            ap.setDate_adopted(adoptionProcessRequest.getDate_adopted());
            ap.setDescription(adoptionProcessRequest.getDescription());
            ap.setAdopter(userRepository.findById(adopterId).orElseThrow(() -> new ResourceNotFoundException("Adopter", "Id", adopterId)));
            ap.setPoster(userRepository.findById(posterId).orElseThrow(() -> new ResourceNotFoundException("Poster", "Id", posterId)));
            return adoptionProcessRepository.save(ap);
        }).orElseThrow(() -> new ResourceNotFoundException("Adoption Process", "Id", adoptionProcessId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteAdoptionProcessById(Integer adoptionProcessId) {
        return adoptionProcessRepository.findById(adoptionProcessId).map(ap -> {
            adoptionProcessRepository.delete(ap);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Adoption process"));
    }
}
