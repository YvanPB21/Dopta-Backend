package com.dopta.repository;

import com.dopta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String nombreUsuario);
    boolean existsByUsername(String nombreUsuario);
    boolean existsByEmailAddress(String email);
}
