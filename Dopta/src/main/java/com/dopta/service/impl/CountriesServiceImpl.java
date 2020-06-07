package com.dopta.service.impl;

import com.dopta.model.Countries;
import com.dopta.model.Departments;
import com.dopta.repository.CountriesRepository;
import com.dopta.repository.DepartmentsRepository;
import com.dopta.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {
    @Autowired
    private CountriesRepository countriesRepository;
    @Override
    public Countries getCountries(Integer id) {

        return countriesRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Countries save(Countries countries) {
        return countriesRepository.save(countries);
    }

    @Override
    public Countries edit(Countries countries, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Countries> listAll() {

        return countriesRepository.findAll();
    }
}
