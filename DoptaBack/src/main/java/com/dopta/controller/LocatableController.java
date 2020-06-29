package com.dopta.controller;

import com.dopta.model.Locatable;
import com.dopta.service.LocatableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class LocatableController {
    @Autowired
    private LocatableService locatableService;

    @GetMapping("{id}")
    public ResponseEntity<Locatable> getById(@PathVariable Integer id) {
        Locatable locatable = locatableService.getLocatable(id);
        if (locatable == null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(locatable));
    }

    @GetMapping
    public ResponseEntity<List<Locatable>> listLocatable() {
        List<Locatable> locatables = new ArrayList<>();
        locatables = locatableService.listAllLocatable();
        if (locatables.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(locatables);
    }

    @PostMapping
    public ResponseEntity<Locatable> newLocatable(@RequestBody Locatable locatable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locatableService.save(locatable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locatable> updateLocatable(@RequestBody Locatable locatable, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(locatableService.edit(locatable, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Locatable> deleteLocatable(@PathVariable Integer id) {
        locatableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
