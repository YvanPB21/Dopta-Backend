package com.dopta.repository;

import com.dopta.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);

    Optional<Comment> findByIdAndAdoptionProcessId(Integer id, Integer adoptionProcessId);
}
