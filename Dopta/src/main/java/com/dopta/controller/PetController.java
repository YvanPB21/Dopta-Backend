package com.dopta.controller;

import com.dopta.dto.pet.CreatePetDTO;
import com.dopta.dto.pet.EditPetDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.service.PetService;
import lombok.RequiredArgsConstructor;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class PetController {
    @Autowired
    private PetService petService;
    @GetMapping("/pets")
    public ResponseEntity<?> getAll() {
        List<PetDTO> pets=petService.listAllPets();

        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pets);
        }
    }
    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Integer id) {
        Optional<PetDTO> result = petService.findById(id);
        if (!result.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);
    }
    @PostMapping("/pets")
    public ResponseEntity<?> newPet (@RequestBody CreatePetDTO petDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(petDto));

    }
    @PutMapping("/pets/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody EditPetDTO petDto, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.edit(petDto,id));
    }
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        petService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}