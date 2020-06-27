package com.tutorial.crud.service;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment getComment(Integer id);
    Comment save(Comment comment);
    Optional<Comment> findById(Integer id);
    Comment edit(Comment comment, Integer id);
    List<Comment> findByAdoptionProcess(AdoptionProcess adoptionProcess);
    void deleteById(Integer id);
}
