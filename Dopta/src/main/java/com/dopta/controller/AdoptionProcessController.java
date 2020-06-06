package com.dopta.controller;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/adoption")
public class AdoptionProcessController {
    @Autowired
    private AdoptionProcessService adoptionProcessService;

    @GetMapping
    public ResponseEntity<List<AdoptionProcess>> listAdoption(@RequestParam(name="PetId", required=false)Integer petId)
    {
        List<AdoptionProcess> adoptions=new ArrayList<>();
        if(null==petId)
        {
            adoptions=adoptionProcessService.listAllAdoptions();
            if(adoptions.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
        }else
        {
                adoptions=adoptionProcessService.findByPet(Pet.builder().id(petId).build());
        }
        return ResponseEntity.ok(adoptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionProcess>getById(@PathVariable Integer id)
    {
        AdoptionProcess adoptionProcess=adoptionProcessService.getAdoption(id);
        if (adoptionProcess==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(adoptionProcess));
    }

    @PostMapping
    public ResponseEntity<AdoptionProcess> newAdoption(@RequestBody AdoptionProcess adoptionProcess)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionProcessService.save(adoptionProcess));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionProcess> updateAdoption(@RequestBody AdoptionProcess adoptionProcess, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionProcessService.edit(adoptionProcess,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AdoptionProcess> deleteAdoption(@PathVariable Integer id)
    {
        adoptionProcessService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
