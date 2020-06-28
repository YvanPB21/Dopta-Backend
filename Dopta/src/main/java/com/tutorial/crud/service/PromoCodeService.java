package com.tutorial.crud.service;


import com.tutorial.crud.entity.PromoCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PromoCodeService {
    PromoCode getPromoCodeById(Integer promoCodeId);
    Page<PromoCode> getAllPromoCodesByPromoId(Integer promoCodeId, Pageable pageable);
    Page<PromoCode> getAllPromoCodesByUserId(Integer userId, Pageable pageable);
    Page<PromoCode> getAllPromoCodes(Pageable pageable);
    PromoCode createPromoCode(PromoCode promoCode, Integer userId, Integer promoId);
    PromoCode editPromoCode(PromoCode promoCode, Integer promoCodeId, Integer userId, Integer promoId);
    ResponseEntity<?> deletePromoCode(Integer promoCodeId);
}
