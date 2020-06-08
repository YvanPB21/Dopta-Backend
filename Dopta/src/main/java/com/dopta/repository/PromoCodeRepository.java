package com.dopta.repository;

import com.dopta.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode,Integer> {
    List<PromoCode> findByIsUsed(Byte isUsed);
}
