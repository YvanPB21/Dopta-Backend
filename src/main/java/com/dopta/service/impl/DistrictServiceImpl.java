package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.District;
import com.dopta.repository.DistrictRepository;
import com.dopta.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
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
        }).orElseThrow(() -> new ResourceNotFoundException("District", "id", id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        districtRepository.deleteById(id);
    }

}
