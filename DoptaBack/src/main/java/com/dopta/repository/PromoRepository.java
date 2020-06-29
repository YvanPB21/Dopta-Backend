package com.dopta.repository;


import com.dopta.model.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {
    Page<Promo> findAllByCorporationId(Integer corporationId, Pageable pageable);
}
