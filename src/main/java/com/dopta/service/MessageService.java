package com.dopta.service;

import com.dopta.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    Page<Message> getAllMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId, Pageable pageable);

    Message getMessageById(Integer messageId);

    Message createMessage(Integer senderId, Integer receiverId, Message message);

    Message updateMessage(Integer MessageId, Message messageDetails);

    ResponseEntity<?> deleteMessage(Integer messageId);

}
