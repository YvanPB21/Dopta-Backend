package com.tutorial.crud.repository;


import com.tutorial.crud.model.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexRepository extends JpaRepository<Sex,Integer> {
}
