package com.dopta.service;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Like;

import java.util.List;

public interface LikeService {
    List<Like> findByAdoptionProcess(AdoptionProcess adoptionProcess);
    Like save(Like like);
    void deleteById(Integer id);
}