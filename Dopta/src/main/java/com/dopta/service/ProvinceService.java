package com.dopta.service;

import com.dopta.model.Districts;
import com.dopta.model.Province;

import java.util.List;

public interface ProvinceService {
    Province getProvince(Integer id);
    List<Province> listAll();
    Province save(Province province);
    Province edit(Province province, Integer id);
    void deleteById(Integer id);
}
