package com.tutorial.crud.service;

import com.tutorial.crud.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MessageService {
    Page<Message> getAllMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId, Pageable pageable);
    Message getMessageById(Integer messageId);
    Message createMessage(Integer senderId, Integer receiverId, Message message);
    Message updateMessage(Integer MessageId, Message messageDetails);
    ResponseEntity<?> deleteMessage(Integer messageId);

}
