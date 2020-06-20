package com.dopta.service;

import com.dopta.model.Country;
import com.dopta.model.Department;

import java.util.List;

public interface CountryService {
    Country getCountry(Integer id);
    List<Country> listAll();
    Country save(Country country);
    Country edit(Country country, Integer id);
    void deleteById(Integer id);
}
