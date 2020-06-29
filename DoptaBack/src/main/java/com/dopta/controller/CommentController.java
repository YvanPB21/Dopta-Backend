package com.dopta.controller;

import com.dopta.model.Comment;
import com.dopta.resource.CommentResource;
import com.dopta.resource.SaveCommentResource;
import com.dopta.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public Page<CommentResource> getAllCommentsByPostId(
            @PathVariable(name = "postId") Integer postId,
            Pageable pageable) {
        Page<Comment> commentPage = commentService.getAllCommentsByAdoptionProcessId(postId, pageable);
        List<CommentResource> resources = commentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentResource getCommentByIdAndPostId(@PathVariable(name = "postId") Integer postId,
                                                   @PathVariable(name = "commentId") Integer commentId) {
        return convertToResource(commentService.getCommentByIdAndAdoptionProcessId(postId, commentId));
    }

    @PostMapping("/posts/{postId}/comments/{posterId}")
    public CommentResource createComment(@PathVariable(name = "postId") Integer postId,
                                         @PathVariable(name = "posterId") Integer posterId,
                                         @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(postId, posterId, convertToEntity(resource)));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public CommentResource updateComment(@PathVariable(name = "postId") Integer postId,
                                         @PathVariable(name = "commentId") Integer commentId,
                                         @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.updateComment(postId, commentId, convertToEntity(resource)));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "postId") Integer postId,
                                           @PathVariable(name = "commentId") Integer commentId) {
        return commentService.deleteComment(postId, commentId);
    }

    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }

}