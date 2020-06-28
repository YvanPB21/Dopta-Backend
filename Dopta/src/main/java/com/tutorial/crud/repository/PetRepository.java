package com.tutorial.crud.repository;


import com.tutorial.crud.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet,Integer> {
    Page<Pet>findAllBySpeciesId(Integer speciesId, Pageable pageable);
    Page<Pet>findAllBySexId(Integer sexId, Pageable pageable);
    Page<Pet>findAllBySizeId(Integer sizeId, Pageable pageable);
    Page<Pet>findAllBySpeciesIdAndSizeId(Integer speciesId, Integer sizeId, Pageable pageable);
    Page<Pet>findAllBySpeciesIdAndSexId(Integer speciesId, Integer sexId, Pageable pageable);
    Page<Pet>findAllBySizeIdAndSexId(Integer sizeId, Integer sexId, Pageable pageable);
    Page<Pet>findAllBySpeciesIdAndSizeIdAndSexId(Integer speciesId, Integer sizeId, Integer sexId, Pageable pageable);

}
