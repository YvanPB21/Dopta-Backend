package com.tutorial.crud.repository;

import com.tutorial.crud.model.User;
import com.tutorial.crud.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
