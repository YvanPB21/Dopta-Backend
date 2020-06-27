package com.tutorial.crud.controller;

import com.tutorial.crud.model.Message;
import com.tutorial.crud.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(Integer id){
        Message message = messageService.getMessage(id);
        if(message==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(message));
    }

    @PostMapping
    public ResponseEntity<Message> newMessage(@RequestBody Message message){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.save(message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(Message message, Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.edit(message, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Integer id){
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
