package com.dopta.controller;

import com.dopta.model.Districts;
import com.dopta.model.Province;
import com.dopta.service.DistrictsService;
import com.dopta.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping
    public ResponseEntity<List<Province>> listProvince(){
        List<Province> province = new ArrayList<>();
        province=provinceService.listAll();
        return ResponseEntity.ok(province);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Province>getById(@PathVariable Integer id){
        Province province = provinceService.getProvince(id);
        if(province==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(province));
    }
    @PostMapping
    public ResponseEntity<Province> newProvince(@RequestBody Province province){
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.save(province));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Province> updateProvince(@RequestBody Province province, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(provinceService.edit(province,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Province> deleteProvince(@PathVariable Integer id) {
        provinceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
