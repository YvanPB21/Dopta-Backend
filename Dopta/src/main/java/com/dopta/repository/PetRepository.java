package com.dopta.repository;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PetRepository extends JpaRepository<Pet,Integer> {
    List<Pet> findBySpecies(Species species);

}
