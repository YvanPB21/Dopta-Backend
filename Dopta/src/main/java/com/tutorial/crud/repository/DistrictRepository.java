package com.tutorial.crud.repository;

import com.tutorial.crud.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District,Integer> {
}
