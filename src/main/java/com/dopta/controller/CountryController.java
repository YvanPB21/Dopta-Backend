package com.dopta.controller;

import com.dopta.model.Country;
import com.dopta.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "https://dopta.netlify.app")
@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getById(@PathVariable Integer id) {
        Country country = countryService.getCountry(id);
        if (country == null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(country));
    }

    @GetMapping("/country")
    public ResponseEntity<List<Country>> listCountry() {
        List<Country> countries = new ArrayList<>();
        countries = countryService.listAllCountry();
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }

    @PostMapping("/country")
    public ResponseEntity<Country> newCountry(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @PutMapping("/countr/{id}")
    public ResponseEntity<Country> updateCountry(@RequestBody Country country, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.edit(country, id));
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Integer id) {
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
