package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Province;
import com.tutorial.crud.repository.ProvinceRepository;
import com.tutorial.crud.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Province getProvince(Integer id) {

        return provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province", "Id", id));
    }
    @Override
    @Transactional
    public Province save(Province province) {

        return provinceRepository.save(province);
    }

    @Override
    public Optional<Province> findById(Integer id) {

        return provinceRepository.findById(id);
    }

    @Override
    public List<Province> listAllProvince() {

        return provinceRepository.findAll();
    }

    @Override
    @Transactional
    public Province edit(Province province, Integer id) {

        return provinceRepository.findById(id).map(province1 -> {
            province1.setName(province.getName());
            return provinceRepository.save(province1);
        }).orElseThrow(() -> new ResourceNotFoundException("Country","id",id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        provinceRepository.deleteById(id);
    }

}
