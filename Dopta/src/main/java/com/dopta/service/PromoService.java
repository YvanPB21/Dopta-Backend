package com.dopta.service;

import com.dopta.model.Corporation;
import com.dopta.model.Promo;
import com.dopta.model.User;

import java.util.List;
import java.util.Optional;

public interface PromoService {
    Promo getPromo(Integer id);
    Promo save(Promo promo);
    Optional<Promo> findById(Integer id);
    List<Promo> listAllPromos();
    Promo edit(Promo promo, Integer id);
    List<Promo>findByCorporation(User corporation);
    void deleteById(Integer id);
}
