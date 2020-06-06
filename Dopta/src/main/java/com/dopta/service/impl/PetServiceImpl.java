package com.dopta.service.Impl;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.User;
import com.dopta.repository.PetRepository;
import com.dopta.repository.UserRepository;
import com.dopta.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet getPet(Integer id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    @Override
    public List<Pet> listAllPets() {
        return petRepository.findAll();
    }

    @Override
    @Transactional
    public Pet edit(Pet pet, Integer id) {
        return petRepository.findById(id).map(p->{
            p.setDate_of_birth(pet.getDate_of_birth());
            p.setImage_url(pet.getImage_url());
            p.setIs_adopted(pet.getIs_adopted());
            p.setName(pet.getName());
            p.setSize(pet.getSize());
            p.setSpecies(pet.getSpecies());
            p.setSex(pet.getSex());
            return petRepository.save(p);
        }).orElse(null);
    }

    @Override
    public List<Pet> findBySpecies(Species species) {
        return petRepository.findBySpecies(species);
    }

    @Override
    @Transactional
    public void deleteById(Integer id)
    {
        petRepository.deleteById(id);
    }
}