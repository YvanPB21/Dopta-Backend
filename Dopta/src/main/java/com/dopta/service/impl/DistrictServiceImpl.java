package com.dopta.service.impl;

import com.dopta.model.District;
import com.dopta.repository.DistrictRepository;
import com.dopta.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository DistrictRepository;
    @Override
    public District getDistrict(Integer id) {

        return DistrictRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public District save(District District) {
        return DistrictRepository.save(District);
    }

    @Override
    public District edit(District District, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<District> listAll() {
        return DistrictRepository.findAll();
    }
}

