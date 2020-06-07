package com.dopta.service.impl;

import com.dopta.model.Message;
import com.dopta.repository.MessageRepository;
import com.dopta.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message getMessage(Integer id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> findById(Integer id) {
        return messageRepository.findById(id);
    }

    @Override
    @Transactional
    public Message edit(Message message, Integer id) {
        return messageRepository.findById(id).map(m->{
            m.setText(message.getText());
            return messageRepository.save(m);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }
}
