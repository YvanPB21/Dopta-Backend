package com.dopta.service.impl;

import com.dopta.repository.PersonRepository;
import com.dopta.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.dopta.model.Person;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPerson(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> listAllPerson() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public Person edit(Person person, Integer id) {
        return personRepository.findById(id).map(p->{
            p.setDate_of_birth(person.getDate_of_birth());
            p.setDni(person.getDni());
            p.setGender(person.getGender());
            p.setNames(person.getNames());
            p.setLast_names(person.getLast_names());
            return personRepository.save(p);
        }).orElse(null);
    }
    public void deleteById(Integer id)
    {
        personRepository.deleteById(id);
    }
}
