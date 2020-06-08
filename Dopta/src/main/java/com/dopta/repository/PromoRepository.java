package com.dopta.repository;

import com.dopta.model.Corporation;
import com.dopta.model.Promo;
import com.dopta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<Promo,Integer> {
    List<Promo> findByCorporation(User corporation);
}
