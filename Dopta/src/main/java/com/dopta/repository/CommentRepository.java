package com.dopta.repository;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByAdoptionProcess(AdoptionProcess adoptionProcess);
}
