package com.dopta.repository;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet,Integer> {
    List<Pet> findBySpecies(Species species);

}
