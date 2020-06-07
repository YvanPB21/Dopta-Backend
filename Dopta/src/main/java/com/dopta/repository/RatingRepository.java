package com.dopta.repository;

import com.dopta.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
