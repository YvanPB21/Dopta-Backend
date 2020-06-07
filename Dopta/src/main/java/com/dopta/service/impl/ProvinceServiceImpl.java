package com.dopta.service.impl;

import com.dopta.model.Districts;
import com.dopta.model.Province;
import com.dopta.repository.DistrictsRepository;
import com.dopta.repository.ProvinceRepository;
import com.dopta.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Override
    public Province getProvince(Integer id) {

        return provinceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public Province edit(Province province, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Province> listAll() {
        return provinceRepository.findAll();
    }
}
