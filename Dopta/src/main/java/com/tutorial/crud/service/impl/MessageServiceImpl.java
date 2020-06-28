package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Message;
import com.tutorial.crud.repository.MessageRepository;
import com.tutorial.crud.repository.PersonRepository;
import com.tutorial.crud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Message> getAllMessagesBySenderIdAndReceiverId(Integer senderId, Integer receiverId, Pageable pageable) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId, pageable);
    }

    @Override
    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElseThrow(()-> new ResourceNotFoundException("Message not found with Id " + messageId));
    }

    @Override
    @Transactional
    public Message createMessage(Integer senderId, Integer receiverId, Message message) {
        Message newMessage = new Message();
        newMessage.setText(message.getText());
        newMessage.setImage_url(message.getText());
        newMessage.setRecording_url(message.getRecording_url());
        newMessage.setVideo_url(message.getVideo_url());
        newMessage.setDate(message.getDate());
        newMessage.setSender(personRepository.findByUserId(senderId).orElse(null));
        newMessage.setReceiver(personRepository.findByUserId(receiverId).orElse(null));
        return messageRepository.save(newMessage);
    }

    @Override
    @Transactional
    public Message updateMessage(Integer messageId, Message messageDetails) {
        return messageRepository.findById(messageId).map(msg->{
            msg.setText(messageDetails.getText());
            msg.setImage_url(messageDetails.getImage_url());
            msg.setVideo_url(messageDetails.getVideo_url());
            msg.setRecording_url(messageDetails.getRecording_url());
            return messageRepository.save(msg);
        }).orElseThrow(()-> new ResourceNotFoundException("Message","Id",messageId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteMessage(Integer messageId) {
        return messageRepository.findById(messageId).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(
                "Message not found with Id " + messageId
        ));
    }
}