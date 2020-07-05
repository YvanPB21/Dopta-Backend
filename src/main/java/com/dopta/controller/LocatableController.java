package com.dopta.controller;

import com.dopta.model.Locatable;
import com.dopta.service.LocatableService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "https://dopta.netlify.app")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LocatableController {
    @Autowired
    private LocatableService locatableService;

    @GetMapping("/locatable/{id}")
    public ResponseEntity<Locatable> getById(@PathVariable Integer id) {
        Locatable locatable = locatableService.getLocatable(id);
        if (locatable == null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(locatable));
    }

    @GetMapping("/locatable")
    public ResponseEntity<List<Locatable>> listLocatable() {
        List<Locatable> locatables = new ArrayList<>();
        locatables = locatableService.listAllLocatable();
        if (locatables.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(locatables);
    }

    @PostMapping("/locatable")
    public ResponseEntity<Locatable> newLocatable(@RequestBody Locatable locatable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locatableService.save(locatable));
    }

    @PutMapping("/locatable/{id}")
    public ResponseEntity<Locatable> updateLocatable(@RequestBody Locatable locatable, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(locatableService.edit(locatable, id));
    }

    @DeleteMapping("/locatable/{id}")
    public ResponseEntity<Locatable> deleteLocatable(@PathVariable Integer id) {
        locatableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
