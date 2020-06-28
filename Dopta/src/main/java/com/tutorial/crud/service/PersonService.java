package com.tutorial.crud.service;

import com.tutorial.crud.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person getPersonByUserId(Integer userId);
    Page<Person> getAllPersons(Pageable pageable);
    Person createPerson(Integer ratingId, Integer genderId, Integer userId, Person person);
    Person updatePerson(Integer personId, Integer ratingId, Integer genderId, Person personDetails);
    ResponseEntity<?> deletePerson(Integer personId);
}
