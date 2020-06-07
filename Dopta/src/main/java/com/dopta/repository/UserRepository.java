package com.dopta.repository;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByusername(String username);
}
