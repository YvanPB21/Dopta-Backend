package com.dopta.repository;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionProcessRepository extends JpaRepository<AdoptionProcess,Integer> {
    List<AdoptionProcess> findByPet(Pet pet);
}