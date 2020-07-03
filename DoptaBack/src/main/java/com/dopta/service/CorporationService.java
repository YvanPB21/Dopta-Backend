package com.dopta.service;

import com.dopta.model.Corporation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CorporationService {
    Corporation getCorporationById(Integer Id);

    Page<Corporation> getAllCorporations(Pageable pageable);

    Corporation createCorporation(Corporation corporation, Integer districtId, Integer locatableId);

    Corporation updateCorporation(Integer corporationId, Integer districtId, Integer locatable, Corporation corporationDetails);

    ResponseEntity<?> deleteCorporation(Integer corporationId);
}