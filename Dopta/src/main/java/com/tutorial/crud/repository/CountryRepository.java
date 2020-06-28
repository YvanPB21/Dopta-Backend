package com.tutorial.crud.repository;


import com.tutorial.crud.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
