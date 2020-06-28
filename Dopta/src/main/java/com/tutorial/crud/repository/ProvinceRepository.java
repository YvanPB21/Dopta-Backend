package com.tutorial.crud.repository;

import com.tutorial.crud.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Integer> {
}
