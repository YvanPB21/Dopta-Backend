package com.dopta.repository;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import com.dopta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionProcessRepository extends JpaRepository<AdoptionProcess,Integer> {
    List<AdoptionProcess> findByPet(Pet pet);
}