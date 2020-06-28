package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Corporation;
import com.tutorial.crud.model.Corporation;
import com.tutorial.crud.repository.*;
import com.tutorial.crud.repository.CorporationRepository;
import com.tutorial.crud.service.CorporationService;
import com.tutorial.crud.service.CorporationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CorporationServiceImpl implements CorporationService {

    @Autowired
    private CorporationRepository corporationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Corporation getCorporationByUserId(Integer userId) {
        return corporationRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Corporation not found with userId " + userId
                ));
    }

    @Override
    public Page<Corporation> getAllCorporations(Pageable pageable) {
        return corporationRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Corporation createCorporation(Integer userId, Corporation corporation) {
        Corporation newCorporation = new Corporation();
        newCorporation.setRuc(corporation.getRuc());
        newCorporation.setCorporationName(corporation.getCorporationName());
        newCorporation.setUser(userRepository.findById(userId).orElse(null));
        return corporationRepository.save(corporation);
    }


    @Override
    @Transactional
    public Corporation updateCorporation(Integer corporationId, Corporation corporationDetails) {
        return corporationRepository.findByUserId(corporationId).map(corp->{
            corp.setCorporationName(corporationDetails.getCorporationName());
            corp.setRuc(corporationDetails.getRuc());
            return corporationRepository.save(corp);
        }).orElseThrow(()->new ResourceNotFoundException("Corporation","Id",corporationId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCorporation(Integer corporationId) {
        return corporationRepository.findByUserId(corporationId).map(per ->{
            corporationRepository.delete(per);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Corporation","Id",corporationId));
    }
}
