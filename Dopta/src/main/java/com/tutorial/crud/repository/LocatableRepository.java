package com.tutorial.crud.repository;

import com.tutorial.crud.model.Locatable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatableRepository extends JpaRepository<Locatable,Integer> { }
