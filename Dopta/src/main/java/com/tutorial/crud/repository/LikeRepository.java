package com.tutorial.crud.repository;


import com.tutorial.crud.entity.AdoptionProcess;
import com.tutorial.crud.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    List<Like> findByAdoptionProcess(AdoptionProcess adoptionProcess);
}
