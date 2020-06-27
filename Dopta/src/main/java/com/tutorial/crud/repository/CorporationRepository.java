package com.tutorial.crud.repository;

import com.tutorial.crud.model.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation,Integer> {
}
