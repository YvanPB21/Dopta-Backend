package com.dopta.service.impl;


import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Promo;
import com.dopta.repository.CorporationRepository;
import com.dopta.repository.PromoRepository;
import com.dopta.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    private PromoRepository promoRepository;
    @Autowired
    private CorporationRepository corporationRepository;

    @Override
    public Promo getPromoById(Integer promoId) {
        return promoRepository.findById(promoId).orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId));
    }

    @Override
    public Page<Promo> getAllPromosByCorporationId(Integer corporationId, Pageable pageable) {
        return promoRepository.findAllByCorporationId(corporationId, pageable);
    }

    @Override
    public Page<Promo> getAllPromos(Pageable pageable) {
        return promoRepository.findAll(pageable);
    }

    @Override
    public Promo createPromo(Promo promo, Integer corporationId) {
        Promo newPromo = new Promo();
        newPromo.setCorporation(corporationRepository.findById(corporationId).orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", corporationId)));
        newPromo.setDescription(promo.getDescription());
        newPromo.setImageUrl(promo.getImageUrl());
        newPromo.setName(promo.getName());
        return promoRepository.save(newPromo);
    }

    @Override
    public Promo editPromo(Promo promo, Integer promoId, Integer corporationId) {
        if (!corporationRepository.existsById(corporationId))
            throw new ResourceNotFoundException("Corporation", "Id", corporationId);
        return promoRepository.findById(promoId).map(pr -> {
            pr.setName(promo.getName());
            pr.setImageUrl(promo.getImageUrl());
            pr.setDescription(promo.getDescription());
            pr.setCorporation(corporationRepository.findById(corporationId).orElseThrow(() -> new ResourceNotFoundException("Promo", "Id", promoId)));
            return promoRepository.save(pr);
        }).orElseThrow(() -> new ResourceNotFoundException("Corporation", "Id", corporationId));
    }

    @Override
    public ResponseEntity<?> deletePromo(Integer promoId) {
        return promoRepository.findById(promoId).map(pr -> {
            promoRepository.delete(pr);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Promo"));
    }
}
