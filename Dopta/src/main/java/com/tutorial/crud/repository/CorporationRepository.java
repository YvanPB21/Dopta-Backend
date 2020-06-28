package com.tutorial.crud.repository;

import com.tutorial.crud.model.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation,Integer> {
    Optional<Corporation> findByUserId(Integer userId);
}
