package com.tutorial.crud.controller;

import com.tutorial.crud.model.AdoptionProcess;
import com.tutorial.crud.model.Like;
import com.tutorial.crud.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{postId}/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping
    public ResponseEntity<List<Like>> listComment(@PathVariable(value = "postId") Integer postId){
        List<Like> likes= new ArrayList<>();
        likes=likeService.findByAdoptionProcess(AdoptionProcess.builder().id(postId).build());
        return ResponseEntity.ok(likes);
    }

    @PostMapping
    public ResponseEntity<Like> newLike(@RequestBody Like like){
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.save(like));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Like> deleteLike(@PathVariable Integer id){
        likeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
