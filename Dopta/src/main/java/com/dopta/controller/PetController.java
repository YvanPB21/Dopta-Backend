package com.dopta.controller;

import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;
    @GetMapping
    public ResponseEntity<List<Pet>> listPet(@RequestParam(name="SpeciesId", required=false)Integer speciesId)
    {
        List<Pet> pets= new ArrayList<>();
        if (null==speciesId){
            pets=petService.listAllPets();
            if (pets.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
        }else
        {
            pets=petService.findBySpecies(Species.builder().id(speciesId).build());
        }
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet>getById(@PathVariable Integer id)
    {
        Pet pet=petService.getPet(id);
        if(pet==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(pet));
    }
    @PostMapping
    public ResponseEntity<Pet> newPet(@RequestBody Pet pet)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(pet));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pet>updatePet(@RequestBody Pet pet, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(petService.edit(pet,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable Integer id)
    {
        petService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}