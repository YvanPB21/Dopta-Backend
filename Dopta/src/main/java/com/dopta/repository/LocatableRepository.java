package com.dopta.repository;

import com.dopta.model.Locatable;
import com.dopta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatableRepository extends JpaRepository<Locatable,Integer> {

}
