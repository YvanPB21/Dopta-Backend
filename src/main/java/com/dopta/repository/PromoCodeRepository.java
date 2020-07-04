package com.dopta.repository;


import com.dopta.model.PromoCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {
    Page<PromoCode> findAllByUserId(Integer corporationId, Pageable pageable);

    Page<PromoCode> findAllByPromoId(Integer promoId, Pageable pageable);
}
