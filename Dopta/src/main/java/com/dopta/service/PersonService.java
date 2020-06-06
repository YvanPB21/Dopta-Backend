package com.dopta.service;

import com.dopta.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person getPerson(Integer id);
    Person save(Person person);
    Optional<Person> findById(Integer id);
    List<Person> listAllPerson();
    Person edit(Person person, Integer id);
    void deleteById(Integer id);
}