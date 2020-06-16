package com.dopta.controller;

import com.dopta.model.Country;
import com.dopta.service.CountryService;
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
public class CountryController {
    @Autowired
    private CountryService countryService;
    @GetMapping
    public ResponseEntity<List<Country>> listCountry(){
        List<Country> country = new ArrayList<>();
        country=countryService.listAll();
        return ResponseEntity.ok(country);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country>getById(@PathVariable Integer id){
        Country country = countryService.getCountry(id);
        if(country==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(country));
    }
    @PostMapping
    public ResponseEntity<Country> newCountry(@RequestBody Country country){
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@RequestBody Country country, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(countryService.edit(country,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Integer id) {
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
