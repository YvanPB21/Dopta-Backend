package com.dopta.service;

import com.dopta.model.Corporation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CorporationService {
    Corporation getCorporationByUserId(Integer userId);

    Page<Corporation> getAllCorporations(Pageable pageable);

    Corporation createCorporation(Integer userId, Corporation corporation);

    Corporation updateCorporation(Integer corporationId, Corporation corporationDetails);

    ResponseEntity<?> deleteCorporation(Integer corporationId);
}