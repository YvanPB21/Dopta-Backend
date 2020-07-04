package com.dopta.controller;

import com.dopta.model.District;
import com.dopta.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/district/{id}")
    public ResponseEntity<District> getById(@PathVariable Integer id) {
        District district = districtService.getDistrict(id);
        if (district == null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(district));
    }

    @GetMapping("/district")
    public ResponseEntity<List<District>> listDistrict() {
        List<District> districts = new ArrayList<>();
        districts = districtService.listAllDistrict();
        if (districts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(districts);
    }

    @PostMapping("/district")
    public ResponseEntity<District> newDistrict(@RequestBody District district) {
        return ResponseEntity.status(HttpStatus.CREATED).body(districtService.save(district));
    }

    @PutMapping("/district/{id}")
    public ResponseEntity<District> updateDistrict(@RequestBody District district, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.edit(district, id));
    }

    @DeleteMapping("/district/{id}")
    public ResponseEntity<District> deleteDistrict(@PathVariable Integer id) {
        districtService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
