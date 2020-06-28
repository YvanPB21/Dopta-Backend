package com.tutorial.crud.service.impl;


import com.tutorial.crud.model.Pet;
import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.repository.PetRepository;
import com.tutorial.crud.repository.SexRepository;
import com.tutorial.crud.repository.SizeRepository;
import com.tutorial.crud.repository.SpeciesRepository;
import com.tutorial.crud.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private SexRepository sexRepository;


    @Override
    public Pet getPetById(Integer petId) {
        return petRepository.findById(petId).orElseThrow(()->new ResourceNotFoundException("Pet","Id",petId));
    }

    @Override
    public Page<Pet> getPetsBySpeciesId(Integer speciesId, Pageable pageable) {
        return petRepository.findAllBySpeciesId(speciesId,pageable);
    }

    @Override
    public Page<Pet> getPetsBySexId(Integer sexId, Pageable pageable) {
        return petRepository.findAllBySexId(sexId,pageable);
    }

    @Override
    public Page<Pet> getPetsBySizeId(Integer sizeId, Pageable pageable) {
        return petRepository.findAllBySizeId(sizeId,pageable);
    }

    @Override
    public Page<Pet> getPetsBySpeciesIdAndSizeId(Integer speciesId, Integer sizeId, Pageable pageable) {
        return petRepository.findAllBySpeciesIdAndSizeId(speciesId,sizeId,pageable);
    }

    @Override
    public Page<Pet> getPetsBySpeciesIdAndSexId(Integer speciesId, Integer sexId, Pageable pageable) {
        return petRepository.findAllBySpeciesIdAndSexId(speciesId,sexId,pageable);
    }

    @Override
    public Page<Pet> getPetsBySizeIdAndSexId(Integer sizeId, Integer sexId, Pageable pageable) {
        return petRepository.findAllBySizeIdAndSexId(sizeId,sexId,pageable);
    }

    @Override
    public Page<Pet> getPetsBySpeciesIdAndSizeIdAndSexId(Integer speciesId, Integer sizeId, Integer sexId, Pageable pageable) {
        return petRepository.findAllBySpeciesIdAndSizeIdAndSexId(speciesId,sizeId,sexId,pageable);
    }

    @Override
    public Page<Pet> getAllPets(Pageable pageable) {
        return petRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Pet createPet(Pet pet, Integer sizeId, Integer speciesId, Integer sexId) {
        Pet newPet=new Pet();
        newPet.setName(pet.getName());
        newPet.setDate_of_birth(pet.getDate_of_birth());
        newPet.setImage_url(pet.getImage_url());
        newPet.setIs_adopted(pet.getIs_adopted());
        newPet.setSex(sexRepository.findById(sexId).orElseThrow(()->new ResourceNotFoundException("Sex","Id",sexId)));
        newPet.setSpecies(speciesRepository.findById(speciesId).orElseThrow(()->new ResourceNotFoundException("Species","Id",speciesId)));
        newPet.setSize(sizeRepository.findById(sizeId).orElseThrow(()->new ResourceNotFoundException("Size","Id",sizeId)));
        return petRepository.save(newPet);
    }

    @Override
    @Transactional
    public Pet editPet(Pet petRequest, Integer petId, Integer sizeId, Integer speciesId, Integer sexId) {
        if(!sexRepository.existsById(sexId))
            throw new ResourceNotFoundException("Sex","Id",sexId);
        if(!speciesRepository.existsById(speciesId))
            throw new ResourceNotFoundException("Species","Id",speciesId);
        if(!sizeRepository.existsById(sizeId))
            throw new ResourceNotFoundException("Size","Id",sizeId);
        return petRepository.findById(petId).map(pet->{
            pet.setName(pet.getName());
            pet.setDate_of_birth(pet.getDate_of_birth());
            pet.setImage_url(pet.getImage_url());
            pet.setIs_adopted(pet.getIs_adopted());
            pet.setSex(sexRepository.findById(sexId).orElseThrow(()->new ResourceNotFoundException("Sex","Id",sexId)));
            pet.setSpecies(speciesRepository.findById(speciesId).orElseThrow(()->new ResourceNotFoundException("Species","Id",speciesId)));
            pet.setSize(sizeRepository.findById(sizeId).orElseThrow(()->new ResourceNotFoundException("Size","Id",sizeId)));
            return petRepository.save(pet);
        }).orElseThrow(()->new ResourceNotFoundException("Pet","Id",petId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deletePetById(Integer petId) {
        return petRepository.findById(petId).map(pet->{
            petRepository.delete(pet);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Pet"));
    }
}