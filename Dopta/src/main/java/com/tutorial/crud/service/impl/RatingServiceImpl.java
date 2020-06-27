package com.tutorial.crud.service.impl;

import com.tutorial.crud.model.Rating;
import com.tutorial.crud.repository.RatingRepository;
import com.tutorial.crud.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating getRating(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> findById(Integer id) {
        return ratingRepository.findById(id);
    }

    @Override
    @Transactional
    public Rating edit(Rating rating, Integer id) {
        return ratingRepository.findById(id).map(r->{
            r.setRating(rating.getRating());
            return ratingRepository.save(r);
        }).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}