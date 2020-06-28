package com.tutorial.crud.repository;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    Page<Like> findByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);
    Optional<Like> findByIdAndAdoptionProcessId(Integer id, Integer adoptionProcessId);
}
