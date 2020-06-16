package com.dopta.service;

import com.dopta.model.District;
import com.dopta.model.Locatable;

import java.util.List;

public interface DistrictService {
    District getDistrict(Integer id);
    List<District> listAll();
    District save(District District);
    District edit(District District, Integer id);
    void deleteById(Integer id);
}
