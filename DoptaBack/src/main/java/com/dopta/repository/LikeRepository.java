package com.dopta.repository;

import com.dopta.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    Page<Like> findByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);

    Optional<Like> findByIdAndAdoptionProcessId(Integer id, Integer adoptionProcessId);
}
