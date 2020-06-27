package com.tutorial.crud.service;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Like;

import java.util.List;

public interface LikeService {
    List<Like> findByAdoptionProcess(AdoptionProcess adoptionProcess);
    Like save(Like like);
    void deleteById(Integer id);
}