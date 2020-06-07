package com.dopta.controller;

import com.dopta.model.Locatable;
import com.dopta.model.Person;
import com.dopta.model.User;
import com.dopta.service.LocatableService;
import com.dopta.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locatable")
public class LocatableController {
    @Autowired
    private LocatableService locatableService;
    @GetMapping
    public ResponseEntity<List<Locatable>> listLocatable(){
        List<Locatable> locatables = new ArrayList<>();
        locatables=locatableService.listAll();
        return ResponseEntity.ok(locatables);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Locatable>getById(@PathVariable Integer id){
        Locatable locatable = locatableService.getLocatable(id);
        if(locatable==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(locatable));
    }
    @PostMapping
    public ResponseEntity<Locatable> newLocatable(@RequestBody Locatable locatable){
        return ResponseEntity.status(HttpStatus.CREATED).body(locatableService.save(locatable));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Locatable> updateLocatable(@RequestBody Locatable locatable, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(locatableService.edit(locatable,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Locatable> deleteLocatable(@PathVariable Integer id) {
        locatableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
