package com.tutorial.crud.service;

import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Locatable;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Country getCountry(Integer id); //ok
    Country save(Country country); //ok
    Optional<Country> findById(Integer id);
    List<Country> listAllCountry(); //ok
    Country edit(Country country, Integer id);
    void deleteById(Integer id);
}
