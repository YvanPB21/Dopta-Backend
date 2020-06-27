package com.tutorial.crud.service.impl;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;
import com.tutorial.crud.repository.CommentRepository;
import com.tutorial.crud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment getComment(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment edit(Comment comment, Integer id) {
        return commentRepository.findById(id).map(c->{
            c.setContent(comment.getContent());
            c.setDate(comment.getDate());
            return commentRepository.save(c);
        }).orElse(null);
    }

    @Override
    public List<Comment> findByAdoptionProcess(AdoptionProcess adoptionProcess) {
        return commentRepository.findByAdoptionProcess(adoptionProcess);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }
}
