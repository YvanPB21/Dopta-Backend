package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Person;
import com.tutorial.crud.repository.GenderRepository;
import com.tutorial.crud.repository.PersonRepository;
import com.tutorial.crud.repository.UserRepository;
import com.tutorial.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService {
    
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Person getPersonByUserId(Integer userId) {
        return personRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Person not found with userId " + userId
                ));
    }

    @Override
    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Person createPerson(Integer ratingId, Integer genderId, Integer userId, Person person) {
        Person newPerson = new Person();
        newPerson.setNames(person.getNames());
        newPerson.setNames(person.getLast_names());
        newPerson.setDni(person.getDni());
        newPerson.setDate_of_birth(person.getDate_of_birth());
        newPerson.setRating(ratingRepository.findById(ratingId).orElse(null));
        newPerson.setGender(genderRepository.findById(genderId).orElse(null));
        newPerson.setUser(userRepository.findById(userId).orElse(null));
        return personRepository.save(newPerson);
    }

    @Override
    @Transactional
    public Person updatePerson(Integer personId, Integer ratingId, Integer genderId, Person personDetails) {
        if(!ratingRepository.existsById(ratingId))
            throw new ResourceNotFoundException("District","Id",ratingId);
        if(!genderRepository.existsById(genderId))
            throw new ResourceNotFoundException("Locatable","Id",genderId);
        return personRepository.findByUserId(personId).map(per->{
            per.setRating(ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating","Id", ratingId)));
            per.setGender(genderRepository.findById(genderId).orElseThrow(()->new ResourceNotFoundException("Gender","Id",genderId)));
            per.setNames(personDetails.getNames());
            per.setLast_names(personDetails.getLast_names());
            per.setDni(personDetails.getDni());
            per.setDate_of_birth(personDetails.getDate_of_birth());
            return personRepository.save(per);
        }).orElseThrow(()->new ResourceNotFoundException("Person","Id",personId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deletePerson(Integer personId) {
        return personRepository.findByUserId(personId).map(per ->{
            personRepository.delete(per);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Person","Id",personId));
    }
}
