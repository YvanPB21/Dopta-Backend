package com.dopta.controller;

import com.dopta.model.Department;
import com.dopta.model.District;
import com.dopta.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Department")
public class DepartmentController {
    @Autowired
    private DepartmentService DepartmentService;
    @GetMapping
    public ResponseEntity<List<Department>> listDepartment(){
        List<Department> Department = new ArrayList<>();
        Department=DepartmentService.listAll();
        return ResponseEntity.ok(Department);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department>getById(@PathVariable Integer id){
        Department Department = DepartmentService.getDepartment(id);
        if(Department==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(Department));
    }
    @PostMapping
    public ResponseEntity<Department> newDepartment(@RequestBody Department Department){
        return ResponseEntity.status(HttpStatus.CREATED).body(DepartmentService.save(Department));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department Department, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(DepartmentService.edit(Department,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Integer id) {
        DepartmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
