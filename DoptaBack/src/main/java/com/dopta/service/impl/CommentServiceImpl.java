package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Comment;
import com.dopta.repository.AdoptionProcessRepository;
import com.dopta.repository.CommentRepository;
import com.dopta.repository.PersonRepository;
import com.dopta.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private AdoptionProcessRepository adoptionProcessRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Comment> getAllCommentsByAdoptionProcessId(Integer adoptionProcessId, Pageable pageable) {
        return commentRepository.findByAdoptionProcessId(adoptionProcessId, pageable);
    }

    @Override
    public Comment getCommentByIdAndAdoptionProcessId(Integer adoptionProcessId, Integer commentId) {
        return commentRepository.findByIdAndAdoptionProcessId(commentId, adoptionProcessId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment not found with Id " + commentId +
                                "and AdoptionProcessId " + adoptionProcessId
                ));
    }

    @Override
    @Transactional
    public Comment createComment(Integer adoptionProcessId, Integer posterId, Comment comment) {
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setDate(comment.getDate());
        newComment.setAdoptionProcess(adoptionProcessRepository.findById(adoptionProcessId).orElse(null));
        newComment.setPoster(personRepository.findById(posterId).orElse(null));

        return commentRepository.save(newComment);
    }

    @Override
    @Transactional
    public Comment updateComment(Integer adoptionProcessId, Integer commentId, Comment commentDetails) {
        if (!adoptionProcessRepository.existsById(adoptionProcessId))
            throw new ResourceNotFoundException("AdoptionProcess", "Id", adoptionProcessId);
        return commentRepository.findById(commentId).map(com -> {
            com.setContent(commentDetails.getContent());
            com.setDate(commentDetails.getDate());
            return commentRepository.save(com);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteComment(Integer adoptionProcessId, Integer commentId) {
        return commentRepository.findByIdAndAdoptionProcessId(commentId, adoptionProcessId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id" + commentId + " and AdoptionProcessId " + adoptionProcessId
        ));
    }
}
