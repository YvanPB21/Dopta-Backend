package com.tutorial.crud.service;

import com.tutorial.crud.model.Rating;

import java.util.Optional;

public interface RatingService {
    Rating getRating(Integer id);
    Rating save(Rating rating);
    Optional<Rating> findById(Integer id);
    Rating edit(Rating rating, Integer id);
    void deleteById(Integer id);
}
