package com.dopta.controller;

import com.dopta.model.District;
import com.dopta.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/District")
public class DistrictController {
    @Autowired
    private com.dopta.service.DistrictService DistrictService;
    @GetMapping
    public ResponseEntity<List<District>> listDistrict(){
        List<District> District = new ArrayList<>();
        District=DistrictService.listAll();
        return ResponseEntity.ok(District);
    }
    @GetMapping("/{id}")
    public ResponseEntity<District>getById(@PathVariable Integer id){
        District District = DistrictService.getDistrict(id);
        if(District==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(District));
    }
    @PostMapping
    public ResponseEntity<District> newDistrict(@RequestBody District District){
        return ResponseEntity.status(HttpStatus.CREATED).body(DistrictService.save(District));
    }
    @PutMapping("/{id}")
    public ResponseEntity<District> updateDistrict(@RequestBody District District, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(DistrictService.edit(District,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<District> deleteDistrict(@PathVariable Integer id) {
        DistrictService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
