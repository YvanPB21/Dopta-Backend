package com.dopta.repository;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
