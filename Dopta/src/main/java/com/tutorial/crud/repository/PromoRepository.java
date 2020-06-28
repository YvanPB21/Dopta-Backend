package com.tutorial.crud.repository;


import com.tutorial.crud.entity.Corporation;
import com.tutorial.crud.entity.Promo;
import com.tutorial.crud.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<Promo,Integer> {
    Page<Promo> findAllByCorporationId(Integer corporationId, Pageable pageable);
}
