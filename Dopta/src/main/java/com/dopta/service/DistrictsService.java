package com.dopta.service;

import com.dopta.model.Districts;
import com.dopta.model.Locatable;

import java.util.List;

public interface DistrictsService {
    Districts getDistricts(Integer id);
    List<Districts> listAll();
    Districts save(Districts districts);
    Districts edit(Districts districts, Integer id);
    void deleteById(Integer id);
}
