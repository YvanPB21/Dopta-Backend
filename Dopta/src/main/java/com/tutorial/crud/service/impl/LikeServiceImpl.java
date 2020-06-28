package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Like;
import com.tutorial.crud.repository.LikeRepository;
import com.tutorial.crud.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private AdoptionProcessRepository adoptionProcessRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Page<Like> getAllLikesByAdoptionProcessId(Integer adoptionProcessId, org.springframework.data.domain.Pageable pageable) {
        return likeRepository.findByAdoptionProcessId(adoptionProcessId, pageable);
    }

    @Override
    public Like getLikeByIdAndAdoptionProcessId(Integer adoptionProcessId, Integer likeId) {
        return likeRepository.findByIdAndAdoptionProcessId(likeId, adoptionProcessId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Like not found with Id " + likeId +
                                " and AdoptionProcessId " + adoptionProcessId
                ));
    }

    @Override
    public Like createLike(Integer adoptionProcessId, Like like) {
        return adoptionProcessRepository.findById(adoptionProcessId).map(post -> {
            like.setAdoptionProcess(post);
            return likeRepository.save(like);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "AdoptionProcess", "Id", adoptionProcessId
        ));
    }

    @Override
    public ResponseEntity<?> deleteLike(Integer adoptionProcessId, Integer likeId) {
        return null;
    }
}