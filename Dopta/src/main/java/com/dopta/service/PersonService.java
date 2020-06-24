package com.dopta.service;

import com.dopta.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    Page<Person> getAllPersons(Pageable pageable);
    List<Person> getAllPersons();
    Person getPersonById(Long personId);
    Person createPerson(Person person);
    Person updatePerson(Long personId, Person personRequest);
    ResponseEntity<?> deletePerson(Long personId);
}
