package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Corporation;
import com.dopta.model.User;
import com.dopta.repository.*;
import com.dopta.repository.CorporationRepository;
import com.dopta.service.CorporationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CorporationServiceImpl implements CorporationService {

    @Autowired
    private CorporationRepository corporationRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private LocatableRepository locatableRepository;

    @Override
    public Corporation getCorporationById(Integer corporationId) {
        return corporationRepository.findById(corporationId).orElseThrow(() -> new ResourceNotFoundException("Corporation", "Id", corporationId));
    }

    @Override
    public Page<Corporation> getAllCorporations(Pageable pageable) {
        return corporationRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Corporation createCorporation(Corporation corporation, Integer districtId, Integer locatableId) {
        Corporation newCorporation = new Corporation();
        newCorporation.setDistrict(districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId)));
        newCorporation.setLocatable(locatableRepository.findById(locatableId).orElseThrow(() -> new ResourceNotFoundException("Locatable", "Id", locatableId)));
        newCorporation.setEmail_address(corporation.getEmail_address());
        newCorporation.setUsername(corporation.getUsername());
        newCorporation.setPassword(corporation.getPassword());
        newCorporation.setProfile_pic_url(corporation.getProfile_pic_url());
        newCorporation.setDate_of_registration(corporation.getDate_of_registration());
        newCorporation.setRuc(corporation.getRuc());
        newCorporation.setCorporationName(corporation.getCorporationName());
        return corporationRepository.save(corporation);
    }


    @Override
    @Transactional
    public Corporation updateCorporation(Integer corporationId, Integer districtId, Integer locatableId, Corporation corporationDetails) {
        if (!districtRepository.existsById(districtId))
            throw new ResourceNotFoundException("District", "Id", districtId);
        if (!locatableRepository.existsById(locatableId))
            throw new ResourceNotFoundException("Locatable", "Id", locatableId);
        return corporationRepository.findById(corporationId).map(corp -> {
            corp.setDistrict(districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId)));
            corp.setLocatable(locatableRepository.findById(locatableId).orElseThrow(() -> new ResourceNotFoundException("Locatable", "Id", locatableId)));
            corp.setEmail_address(corporationDetails.getEmail_address());
            corp.setUsername(corporationDetails.getUsername());
            corp.setPassword(corporationDetails.getPassword());
            corp.setProfile_pic_url(corporationDetails.getProfile_pic_url());
            corp.setDate_of_registration(corporationDetails.getDate_of_registration());
            corp.setCorporationName(corporationDetails.getCorporationName());
            corp.setRuc(corporationDetails.getRuc());
            return corporationRepository.save(corp);
        }).orElseThrow(() -> new ResourceNotFoundException("Corporation", "Id", corporationId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCorporation(Integer corporationId) {
        return corporationRepository.findById(corporationId).map(per -> {
            corporationRepository.delete(per);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Corporation", "Id", corporationId));
    }
}
