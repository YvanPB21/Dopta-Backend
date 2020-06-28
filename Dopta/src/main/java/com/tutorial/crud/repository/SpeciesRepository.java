package com.tutorial.crud.repository;


import com.tutorial.crud.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species,Integer> {


}
