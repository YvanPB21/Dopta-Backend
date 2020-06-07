package com.dopta.controller;

import com.dopta.model.Districts;
import com.dopta.model.Locatable;
import com.dopta.service.DistrictsService;
import com.dopta.service.LocatableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/districts")
public class DistrictsController {
    @Autowired
    private DistrictsService districtsService;
    @GetMapping
    public ResponseEntity<List<Districts>> listDistricts(){
        List<Districts> districts = new ArrayList<>();
        districts=districtsService.listAll();
        return ResponseEntity.ok(districts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Districts>getById(@PathVariable Integer id){
        Districts districts = districtsService.getDistricts(id);
        if(districts==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(districts));
    }
    @PostMapping
    public ResponseEntity<Districts> newDistricts(@RequestBody Districts districts){
        return ResponseEntity.status(HttpStatus.CREATED).body(districtsService.save(districts));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Districts> updateDistricts(@RequestBody Districts districts, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(districtsService.edit(districts,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Districts> deleteDistricts(@PathVariable Integer id) {
        districtsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
