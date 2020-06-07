package com.dopta.controller;

import com.dopta.model.Departments;
import com.dopta.model.Districts;
import com.dopta.service.DepartmentsService;
import com.dopta.service.DistrictsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentsService departmentsService;
    @GetMapping
    public ResponseEntity<List<Departments>> listDepartments(){
        List<Departments> departments = new ArrayList<>();
        departments=departmentsService.listAll();
        return ResponseEntity.ok(departments);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Departments>getById(@PathVariable Integer id){
        Departments departments = departmentsService.getDepartments(id);
        if(departments==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(departments));
    }
    @PostMapping
    public ResponseEntity<Departments> newDepartments(@RequestBody Departments departments){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentsService.save(departments));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Departments> updateDepartments(@RequestBody Departments departments, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(departmentsService.edit(departments,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Departments> deleteDepartments(@PathVariable Integer id) {
        departmentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
