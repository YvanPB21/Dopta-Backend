package com.dopta.repository;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionProcessRepository extends JpaRepository<AdoptionProcess, Integer> {
    Page<AdoptionProcess> findAllByPet(Pet pet, Pageable pageable);

    Page<AdoptionProcess> findAllByPetId(Integer petId, Pageable pageable);

    /*TODO implementar estas dos, revisar si es user u otra clase*/
    Page<AdoptionProcess> findAllByAdopterId(Integer userId, Pageable pageable);

    Page<AdoptionProcess> findAllByPosterId(Integer userId, Pageable pageable);
}