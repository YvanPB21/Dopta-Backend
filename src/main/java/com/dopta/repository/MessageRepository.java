package com.dopta.repository;

import com.dopta.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Page<Message> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId, Pageable pageable);
}
