package com.dopta.controller;

import com.dopta.model.Countries;
import com.dopta.model.Departments;
import com.dopta.service.CountriesService;
import com.dopta.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountriesController {
    @Autowired
    private CountriesService countriesService;
    @GetMapping
    public ResponseEntity<List<Countries>> listCountries(){
        List<Countries> countries = new ArrayList<>();
        countries=countriesService.listAll();
        return ResponseEntity.ok(countries);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Countries>getById(@PathVariable Integer id){
        Countries countries = countriesService.getCountries(id);
        if(countries==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(countries));
    }
    @PostMapping
    public ResponseEntity<Countries> newCountries(@RequestBody Countries countries){
        return ResponseEntity.status(HttpStatus.CREATED).body(countriesService.save(countries));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Countries> updateCountries(@RequestBody Countries countries, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(countriesService.edit(countries,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Countries> deleteCountries(@PathVariable Integer id) {
        countriesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
