package com.tutorial.crud.controller;

import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Province;
import com.tutorial.crud.service.CountryService;
import com.tutorial.crud.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @GetMapping("{id}")
    public ResponseEntity<Province> getById(@PathVariable Integer id)
    {
        Province province=provinceService.getProvince(id);
        if(province==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(province));
    }
    @GetMapping
    public ResponseEntity<List<Province>> listProvince()
    {
        List<Province> provinces=new ArrayList<>();
        provinces=provinceService.listAllProvince();
        if(provinces.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(provinces);
    }
    @PostMapping
    public ResponseEntity<Province> newProvince(@RequestBody Province province)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.save(province));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Province> updateProvince(@RequestBody Province province, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(provinceService.edit(province,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Province> deleteProvince(@PathVariable Integer id)
    {
        provinceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

