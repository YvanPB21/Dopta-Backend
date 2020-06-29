package com.dopta.service;

import com.dopta.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getAllCommentsByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable);

    Comment getCommentByIdAndAdoptionProcessId(Integer adoptionProcessId, Integer commentId);

    Comment createComment(Integer adoptionProcessId, Integer posterId, Comment comment);

    Comment updateComment(Integer adoptionProcessId, Integer commentId, Comment commentDetails);

    ResponseEntity<?> deleteComment(Integer adoptionProcessId, Integer commentId);
}
