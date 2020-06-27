package com.tutorial.crud.service;

import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    Province getProvince(Integer id); //ok
    Province save(Province province); //ok
    Optional<Province> findById(Integer id);
    List<Province> listAllProvince(); //ok
    Province edit(Province province, Integer id);
    void deleteById(Integer id);
}
