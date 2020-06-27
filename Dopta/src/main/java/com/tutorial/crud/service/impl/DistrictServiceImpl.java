package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.District;
import com.tutorial.crud.repository.CountryRepository;
import com.tutorial.crud.repository.DistrictRepository;
import com.tutorial.crud.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public District getDistrict(Integer id) {

        return districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District", "Id", id));
    }
    @Override
    @Transactional
    public District save(District district) {

        return districtRepository.save(district);
    }

    @Override
    public Optional<District> findById(Integer id) {

        return districtRepository.findById(id);
    }

    @Override
    public List<District> listAllDistrict() {

        return districtRepository.findAll();
    }

    @Override
    @Transactional
    public District edit(District district, Integer id) {

        return districtRepository.findById(id).map(district1 -> {
            district1.setName(district.getName());
            return districtRepository.save(district1);
        }).orElseThrow(() -> new ResourceNotFoundException("District","id",id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        districtRepository.deleteById(id);
    }

}
