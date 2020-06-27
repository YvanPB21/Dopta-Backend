package com.tutorial.crud.service;

import com.tutorial.crud.model.Message;

import java.util.Optional;

public interface MessageService {
    Message getMessage(Integer id);
    Message save(Message message);
    Optional<Message> findById(Integer id);
    Message edit(Message message, Integer id);
    void deleteById(Integer id);
}
