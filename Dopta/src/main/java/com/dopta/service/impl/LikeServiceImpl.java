package com.dopta.service.impl;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Like;
import com.dopta.repository.LikeRepository;
import com.dopta.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> findByAdoptionProcess(AdoptionProcess adoptionProcess) {
        return likeRepository.findByAdoptionProcess(adoptionProcess);
    }

    @Override
    @Transactional
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        likeRepository.deleteById(id);
    }
}