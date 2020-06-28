package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Locatable;
import com.tutorial.crud.repository.CountryRepository;
import com.tutorial.crud.repository.LocatableRepository;
import com.tutorial.crud.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country getCountry(Integer id) {

        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Countries", "Id", id));
    }
    @Override
    @Transactional
    public Country save(Country country) {

        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> findById(Integer id) {

        return countryRepository.findById(id);
    }

    @Override
    public List<Country> listAllCountry() {

        return countryRepository.findAll();
    }

    @Override
    @Transactional
    public Country edit(Country country, Integer id) {

        return countryRepository.findById(id).map(country1 -> {
            country1.setName(country.getName());
            return countryRepository.save(country1);
        }).orElseThrow(() -> new ResourceNotFoundException("Country","id",id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        countryRepository.deleteById(id);
    }

}
