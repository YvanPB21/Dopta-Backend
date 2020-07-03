package com.dopta.controller;

import com.dopta.model.Province;
import com.dopta.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/province/{id}")
    public ResponseEntity<Province> getById(@PathVariable Integer id) {
        Province province = provinceService.getProvince(id);
        if (province == null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(province));
    }

    @GetMapping("/province")
    public ResponseEntity<List<Province>> listProvince() {
        List<Province> provinces = new ArrayList<>();
        provinces = provinceService.listAllProvince();
        if (provinces.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(provinces);
    }

    @PostMapping("/province")
    public ResponseEntity<Province> newProvince(@RequestBody Province province) {
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.save(province));
    }

    @PutMapping("/province/{id}")
    public ResponseEntity<Province> updateProvince(@RequestBody Province province, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(provinceService.edit(province, id));
    }

    @DeleteMapping("/province/{id}")
    public ResponseEntity<Province> deleteProvince(@PathVariable Integer id) {
        provinceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

