package com.tutorial.crud.repository;

import com.tutorial.crud.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
