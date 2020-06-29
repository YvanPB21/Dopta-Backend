package com.dopta.controller;

import com.dopta.model.Person;
import com.dopta.resource.PersonResource;
import com.dopta.resource.SavePersonResource;
import com.dopta.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public Page<PersonResource> getAllPersons(Pageable pageable) {
        List<PersonResource> personResources = personService.getAllPersons(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(personResources, pageable, personResources.size());
    }

    @GetMapping("/persons/{userId}")
    public PersonResource getPersonByUserId(@PathVariable(name = "userId") Integer userId) {
        return convertToResource(personService.getPersonByUserId(userId));
    }

    @PostMapping("/persons/{userId}/{ratingId}/{genderId}")
    public PersonResource createPerson(@PathVariable(name = "userId") Integer userId,
                                       @PathVariable(name = "ratingId") Integer ratingId,
                                       @PathVariable(name = "genderId") Integer genderId,
                                       @Valid @RequestBody SavePersonResource resource) {
        return convertToResource(personService.createPerson(ratingId, genderId, userId, convertToEntity(resource)));
    }

    @PutMapping("/persons/{userId}/{ratingId}/{genderId}")
    public PersonResource updatePerson(@PathVariable(name = "userId") Integer userId,
                                       @PathVariable(name = "ratingId") Integer ratingId,
                                       @PathVariable(name = "genderId") Integer genderId,
                                       @Valid @RequestBody SavePersonResource resource) {
        return convertToResource(personService.updatePerson(userId, ratingId, genderId, convertToEntity(resource)));
    }

    @DeleteMapping("/persons/{userId}")
    public ResponseEntity<?> deletePerson(@PathVariable(name = "userId") Integer userId) {
        return personService.deletePerson(userId);
    }

    private Person convertToEntity(SavePersonResource resource) {
        return mapper.map(resource, Person.class);
    }

    private PersonResource convertToResource(Person entity) {
        return mapper.map(entity, PersonResource.class);
    }

}
