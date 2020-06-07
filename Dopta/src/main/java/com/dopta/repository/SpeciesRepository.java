package com.dopta.repository;

import com.dopta.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species,Integer> {

}
