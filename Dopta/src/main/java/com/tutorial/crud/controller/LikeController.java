package com.tutorial.crud.controller;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Like;
import com.tutorial.crud.resource.LikeResource;
import com.tutorial.crud.resource.SaveLikeResource;
import com.tutorial.crud.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/api")
public class LikeController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private LikeService likeService;

    @GetMapping("/posts/{postId}/likes")
    public Page<LikeResource> getAllLikesByPostId(
            @PathVariable(name = "postId") Integer postId,
            Pageable pageable) {
        Page<Like> likePage = likeService.getAllLikesByAdoptionProcessId(postId, pageable);
        List<LikeResource> resources = likePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/posts/{postId}/likes/{likeId}")
    public LikeResource getLikesByIdAndPostId(@PathVariable(name = "postId") Integer postId,
                                              @PathVariable(name = "likeId") Integer likeId) {
        return convertToResource(likeService.getLikeByIdAndAdoptionProcessId(postId, likeId));
    }

    @PostMapping("/posts/{postId}/likes/{likerId}")
    public LikeResource createLike(@PathVariable(name = "postId") Integer postId,
                                         @PathVariable(name = "likerId") Integer likerId,
                                         @Valid @RequestBody SaveLikeResource resource) {
        return convertToResource(likeService.createLike(postId, likerId, convertToEntity(resource)));
    }

    @DeleteMapping("/posts/{postId}/likes/{likeId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "postId") Integer postId,
                                           @PathVariable(name = "likeId") Integer likeId) {
        return likeService.deleteLike(postId, likeId);
    }

    private Like convertToEntity(SaveLikeResource resource) {
        return mapper.map(resource, Like.class);
    }

    private LikeResource convertToResource(Like entity) {
        return mapper.map(entity, LikeResource.class);
    }


}
