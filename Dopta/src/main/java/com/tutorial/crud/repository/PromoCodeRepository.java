package com.tutorial.crud.repository;


import com.tutorial.crud.entity.PromoCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode,Integer> {
    Page<PromoCode> findAllByUserId(Integer corporationId, Pageable pageable);
    Page<PromoCode> findAllByPromoId(Integer promoId, Pageable pageable);
}
