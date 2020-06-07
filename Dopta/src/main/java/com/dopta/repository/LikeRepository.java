package com.dopta.repository;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    List<Like> findByAdoptionProcess(AdoptionProcess adoptionProcess);
}
