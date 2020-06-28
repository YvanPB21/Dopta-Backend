package com.tutorial.crud.repository;


import com.tutorial.crud.entity.AdoptionProcess;
import com.tutorial.crud.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionProcessRepository extends JpaRepository<AdoptionProcess,Integer> {
    Page<AdoptionProcess> findAllByPet(Pet pet, Pageable pageable);
    Page<AdoptionProcess> findAllByPetId(Integer petId, Pageable pageable);
    /*TODO implementar estas dos, revisar si es user u otra clase*/
    Page<AdoptionProcess> findAllByAdopterId(Integer userId, Pageable pageable);
    Page<AdoptionProcess> findAllByPosterId(Integer userId, Pageable pageable);
}