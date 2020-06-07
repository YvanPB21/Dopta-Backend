package com.dopta.service;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Comment;

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
