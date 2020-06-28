package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;
import com.tutorial.crud.repository.CommentRepository;
import com.tutorial.crud.repository.PersonRepository;
import com.tutorial.crud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Comment not found with Id " +commentId+
                                "and AdoptionProcessId " + adoptionProcessId
                ));
    }

    @Override
    public Comment createComment(Integer adoptionProcessId, Integer posterId, Comment comment) {
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setDate(comment.getDate());
        newComment.setAdoptionProcess(adoptionProcessRepository.findById(adoptionProcessId).orElse(null));
        newComment.setPoster(personRepository.findById(posterId).orElse(null));

        return commentRepository.save(newComment);
    }

    @Override
    public Comment updateComment(Integer adoptionProcessId, Integer commentId, Comment commentDetails) {
        if(!adoptionProcessRepository.existsById(adoptionProcessId))
            throw new ResourceNotFoundException("AdoptionProcess","Id",adoptionProcessId);
        return commentRepository.findById(commentId).map(com->{
            com.setContent(commentDetails.getContent());
            com.setDate(commentDetails.getDate());
        }).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
    }

    @Override
    public ResponseEntity<?> deleteComment(Integer adoptionProcessId, Integer commentId) {
        return commentRepository.findByIdAndAdoptionProcessId(commentId, adoptionProcessId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().body();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id" + commentId + " and AdoptionProcessId " + adoptionProcessId
        ));
    }
}
