package com.tutorial.crud.controller;

import com.tutorial.crud.model.Person;
import com.tutorial.crud.model.User;
import com.tutorial.crud.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> listPerson(){
        List<Person> persons = new ArrayList<>();
        persons=personService.listAllPerson();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        Person person = personService.getPerson(id);
        if(person==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(person));
    }

    @PostMapping
    public ResponseEntity<Person> newPerson(@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(personService.edit(person,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Integer id){
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
