package com.tutorial.crud.service;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LikeService {
    Page<Like> getAllLikesByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);
    Like getLikeByIdAndAdoptionProcessId(Integer adoptionProcessId, Integer likeId);
    Like createLike(Integer adoptionProcessId, Like like);
    ResponseEntity<?> deleteLike(Integer adoptionProcessId, Integer likeId);
}
