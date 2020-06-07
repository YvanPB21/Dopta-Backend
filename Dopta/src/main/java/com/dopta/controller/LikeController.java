package com.dopta.controller;

import com.dopta.model.AdoptionProcess;
import com.dopta.model.Comment;
import com.dopta.model.Like;
import com.dopta.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/{postId}/likes")
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
