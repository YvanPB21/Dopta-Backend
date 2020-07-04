package com.dopta.repository;

import com.dopta.model.Locatable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocatableRepository extends JpaRepository<Locatable, Integer> {
}
