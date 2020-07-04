package com.dopta.service;


import com.dopta.model.PromoCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PromoCodeService {
    PromoCode getPromoCodeById(Integer promoCodeId);

    Page<PromoCode> getAllPromoCodesByPromoId(Integer promoCodeId, Pageable pageable);

    Page<PromoCode> getAllPromoCodesByUserId(Integer userId, Pageable pageable);

    Page<PromoCode> getAllPromoCodes(Pageable pageable);

    PromoCode createPromoCode(PromoCode promoCode, Integer userId, Integer promoId);

    PromoCode editPromoCode(PromoCode promoCode, Integer promoCodeId, Integer userId, Integer promoId);

    ResponseEntity<?> deletePromoCode(Integer promoCodeId);
}
