package com.tutorial.crud.controller;

import com.tutorial.crud.model.Message;
import com.tutorial.crud.resource.MessageResource;
import com.tutorial.crud.resource.SaveMessageResource;
import com.tutorial.crud.service.MessageService;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{senderId}/{receiverId}")
    public Page<MessageResource> getAllMessagesBySenderIdAndReceiverId(
            @PathVariable(name = "senderId") Integer senderId,
            @PathVariable(name = "receiverId") Integer receiverId,
            Pageable pageable) {
        Page<Message> messagePage=messageService.getAllMessagesBySenderIdAndReceiverId(senderId,receiverId, pageable);
        List<MessageResource> resources = messagePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/messages/{messageId}")
    public MessageResource getMessageById(@PathVariable(name = "messageId") Integer messageId){
        return convertToResource(messageService.getMessageById(messageId));
    }

    @PostMapping("/messages/{messageId}/{senderId}/{receiverId}")
    public MessageResource createMessage(@PathVariable(name = "messageId") Integer messageId,
                                         @PathVariable(name = "senderId") Integer senderId,
                                         @PathVariable(name = "receiverId") Integer receiverId,
                                         @Valid @RequestBody SaveMessageResource resource){
        return convertToResource(messageService.createMessage(senderId, receiverId, convertToEntity(resource)));
    }

    @PutMapping("/messages/{messageId}")
    public MessageResource updateMessage(@PathVariable(name = "messageId") Integer messageId,
                                         @Valid @RequestBody SaveMessageResource resource){
        return convertToResource(messageService.updateMessage(messageId, convertToEntity(resource)));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId") Integer messageId){
        return messageService.deleteMessage(messageId);
    }

    private Message convertToEntity(SaveMessageResource resource){
        return mapper.map(resource, Message.class);
    }
    private MessageResource convertToResource(Message entity){
        return mapper.map(entity, MessageResource.class);
    }
}
