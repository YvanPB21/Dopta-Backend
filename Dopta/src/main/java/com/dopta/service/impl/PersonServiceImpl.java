package com.dopta.service.impl;

import com.dopta.model.Person;
import com.dopta.repository.PersonRepository;
import com.dopta.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long personId) {
        return null;
    }

    @Override
    public Person createPerson(Person person) {
        return null;
    }

    @Override
    public Person updatePerson(Long personId, Person personRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePerson(Long personId) {
        return null;
    }
}
