package com.dopta.controller;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Comment;
import com.dopta.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> listComment(@PathVariable(value = "postId") Integer postId){
        List<Comment> comments= new ArrayList<>();
        comments=commentService.findByAdoptionProcess(AdoptionProcess.builder().id(postId).build());
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> newComment(@RequestBody Comment comment){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.save(comment));
    }

    @PutMapping
    public ResponseEntity<Comment> updateComment(Comment comment, Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.edit(comment, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer id){
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
