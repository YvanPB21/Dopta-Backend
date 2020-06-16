package com.dopta.service.impl;

import com.dopta.model.Country;
import com.dopta.repository.CountryRepository;
import com.dopta.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public Country getCountry(Integer id) {

        return countryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country edit(Country country, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Country> listAll() {

        return countryRepository.findAll();
    }
}
