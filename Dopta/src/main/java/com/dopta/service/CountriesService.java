package com.dopta.service;

import com.dopta.model.Countries;
import com.dopta.model.Departments;

import java.util.List;

public interface CountriesService {
    Countries getCountries(Integer id);
    List<Countries> listAll();
    Countries save(Countries countries);
    Countries edit(Countries countries, Integer id);
    void deleteById(Integer id);
}
