package com.tutorial.crud.service;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Page<Comment> getAllCommentsByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);
    Comment getCommentByIdAndAdoptionProcessId(Integer adoptionProcessId, Integer commentId);
    Comment createComment(Integer adoptionProcessId, Comment comment);
    Comment updateComment(Integer adoptionProcessId, Integer commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Integer adoptionProcessId, Integer commentId);
}
