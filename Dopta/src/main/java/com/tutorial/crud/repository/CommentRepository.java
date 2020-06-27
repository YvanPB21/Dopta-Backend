package com.tutorial.crud.repository;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByAdoptionProcess(AdoptionProcess adoptionProcess);
}
