package com.dopta.repository;

import com.dopta.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Countries,Integer> {
}
