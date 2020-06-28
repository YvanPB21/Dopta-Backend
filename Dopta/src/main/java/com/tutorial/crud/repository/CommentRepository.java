package com.tutorial.crud.repository;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Page<Comment> findByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);
    Optional<Comment> findByIdAndAdoptionProcessId(Integer id, Integer adoptionProcessId);
}
