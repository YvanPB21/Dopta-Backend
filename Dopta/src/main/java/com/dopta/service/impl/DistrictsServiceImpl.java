package com.dopta.service.impl;

import com.dopta.model.Districts;
import com.dopta.model.Locatable;
import com.dopta.repository.DistrictsRepository;
import com.dopta.repository.LocatableRepository;
import com.dopta.service.DistrictsService;
import com.dopta.service.LocatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class DistrictsServiceImpl implements DistrictsService {
    @Autowired
    private DistrictsRepository districtsRepository;
    @Override
    public Districts getDistricts(Integer id) {

        return districtsRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Districts save(Districts districts) {
        return districtsRepository.save(districts);
    }

    @Override
    public Districts edit(Districts districts, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Districts> listAll() {
        return districtsRepository.findAll();
    }
}

