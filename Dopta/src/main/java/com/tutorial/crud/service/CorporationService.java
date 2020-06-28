package com.tutorial.crud.service;

import com.tutorial.crud.model.Corporation;
import com.tutorial.crud.model.Corporation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CorporationService {
    Corporation getCorporationByUserId(Integer userId);
    Page<Corporation> getAllCorporations(Pageable pageable);
    Corporation createCorporation(Integer userId, Corporation corporation);
    Corporation updateCorporation(Integer corporationId, Corporation corporationDetails);
    ResponseEntity<?> deleteCorporation(Integer corporationId);
}