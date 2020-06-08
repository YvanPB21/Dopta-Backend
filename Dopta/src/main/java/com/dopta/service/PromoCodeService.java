package com.dopta.service;

import com.dopta.model.Pet;
import com.dopta.model.PromoCode;
import com.dopta.model.Species;

import java.util.List;
import java.util.Optional;

public interface PromoCodeService {
    PromoCode getPromoCode(Integer id);
    PromoCode save(PromoCode promoCode);
    Optional<PromoCode> findById(Integer id);
    List<PromoCode> listAllPromoCodes();
    PromoCode edit(PromoCode promoCode, Integer id);
    List<PromoCode>findByIsUsed(Byte isUsed);
    void deleteById(Integer id);
}
