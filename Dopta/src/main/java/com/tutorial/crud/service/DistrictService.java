package com.tutorial.crud.service;

import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.District;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    District getDistrict(Integer id); //ok
    District save(District district); //ok
    Optional<District> findById(Integer id);
    List<District> listAllDistrict(); //ok
    District edit(District district, Integer id);
    void deleteById(Integer id);

}
