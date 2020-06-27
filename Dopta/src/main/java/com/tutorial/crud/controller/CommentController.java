package com.tutorial.crud.controller;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Comment;
import com.tutorial.crud.resource.CommentResource;
import com.tutorial.crud.resource.SaveCommentResource;
import com.tutorial.crud.service.CommentService;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public Page<CommentResource> getAllCommentsByPostId(@PathVariable(name = "postId") Integer postId, Pageable pageable){
        Page<Comment> commentPage = commentService.getAllCommentsByAdoptionProcessId(postId, pageable);
        List<CommentResource> resources = commentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping
    public ResponseEntity<Comment> newComment(@RequestBody Comment comment){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.save(comment));
    }

    @PostMapping("/posts/{postId}/comments")
    public CommentResource createComment(@PathVariable(name = "postId") Long postId,
                                         @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(postId, convertToEntity(resource)));
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

    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }
}
