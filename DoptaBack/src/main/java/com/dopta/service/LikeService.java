package com.dopta.service;

import com.dopta.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LikeService {
    Page<Like> getAllLikesByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);

    Like getLikeByIdAndAdoptionProcessId(Integer adoptionProcessId, Integer likeId);

    Like createLike(Integer adoptionProcessId, Integer likerId, Like like);

    ResponseEntity<?> deleteLike(Integer adoptionProcessId, Integer likeId);
}
