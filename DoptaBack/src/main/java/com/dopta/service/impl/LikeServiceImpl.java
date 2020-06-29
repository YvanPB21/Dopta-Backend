package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Like;
import com.dopta.repository.AdoptionProcessRepository;
import com.dopta.repository.LikeRepository;
import com.dopta.repository.PersonRepository;
import com.dopta.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private AdoptionProcessRepository adoptionProcessRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PersonRepository personRepository;

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
    @Transactional
    public Like createLike(Integer adoptionProcessId, Integer likerId, Like like) {
        Like newLike = new Like();
        newLike.setLiker(personRepository.findById(likerId).orElse(null));
        newLike.setAdoptionProcess(adoptionProcessRepository.findById(adoptionProcessId).orElse(null));

        return likeRepository.save(newLike);
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteLike(Integer adoptionProcessId, Integer likeId) {
        return likeRepository.findByIdAndAdoptionProcessId(likeId, adoptionProcessId).map(comment -> {
            likeRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id" + likeId + " and AdoptionProcessId " + adoptionProcessId
        ));
    }
}