package com.dopta.controller;


import com.dopta.model.Person;
import com.dopta.resource.PersonResource;
import com.dopta.resource.SavePersonResource;
import com.dopta.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PersonService personService;

   /* @GetMapping
    public ResponseEntity<Page<PersonResource>> getAllPersons(Pageable pageable) {
        Page<Person> personPage = personService.getAllPersons(pageable);
        List<PersonResource> resources = personPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        Page<PersonResource> page=new PageImpl<>(resources);
        //return new PageImpl<>(resources, pageable, resources.size());
        return new ResponseEntity<Page<PersonResource>>(page, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<PersonResource>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        List<PersonResource> personResources = persons.stream().map(this::convertToResource).collect(Collectors.toList());
        return new ResponseEntity<List<PersonResource>>(personResources, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonResource> createPerson(@Valid @RequestBody SavePersonResource resource)  {
        Person person = convertToEntity(resource);
        PersonResource personResource=convertToResource(personService.createPerson(person));
        //localhost:8080/api/outcomes/1
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/{id}")
                .buildAndExpand(personResource.getPersonId()).toUri();
        return ResponseEntity.created(location).build();
    }



    private Person convertToEntity(SavePersonResource resource) {
        return mapper.map(resource, Person.class);
    }

    private PersonResource convertToResource(Person entity) {
        return mapper.map(entity, PersonResource.class);
    }

}