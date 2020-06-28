package com.tutorial.crud.repository;

import com.tutorial.crud.model.Comment;
import com.tutorial.crud.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    Page<Message> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId, Pageable pageable);
}