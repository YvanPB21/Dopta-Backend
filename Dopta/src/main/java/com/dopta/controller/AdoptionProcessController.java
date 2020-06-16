package com.dopta.controller;

import com.dopta.dto.adoptionprocess.AdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.CreateAdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.EditAdoptionProcessDTO;
import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import com.dopta.model.User;
import com.dopta.service.AdoptionProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/adoption")
public class AdoptionProcessController {
    @Autowired
    private AdoptionProcessService adoptionProcessService;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<AdoptionProcessDTO> adoptions=adoptionProcessService.listAllAdoptions();
        if (adoptions.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(adoptions);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id)
    {
        Optional<AdoptionProcessDTO> result = adoptionProcessService.findById(id);
        if(!result.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> newAdoption(@RequestBody CreateAdoptionProcessDTO adoptionProcessDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionProcessService.save(adoptionProcessDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdoption(@RequestBody EditAdoptionProcessDTO adoptionProcess, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionProcessService.edit(adoptionProcess,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAdoption(@PathVariable Integer id)
    {
        adoptionProcessService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
