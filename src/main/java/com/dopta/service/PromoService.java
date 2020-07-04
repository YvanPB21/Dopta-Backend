package com.dopta.service;


import com.dopta.model.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PromoService {
    Promo getPromoById(Integer promoId);

    Page<Promo> getAllPromosByCorporationId(Integer corporationId, Pageable pageable);

    Page<Promo> getAllPromos(Pageable pageable);

    Promo createPromo(Promo promo, Integer corporationId);

    Promo editPromo(Promo promo, Integer promoId, Integer corporationId);

    ResponseEntity<?> deletePromo(Integer promoId);
}
