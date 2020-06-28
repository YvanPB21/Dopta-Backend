package com.tutorial.crud.controller;

import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Department;
import com.tutorial.crud.service.CountryService;
import com.tutorial.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id)
    {
        Department department=departmentService.getDepartment(id);
        if(department==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(department));
    }
    @GetMapping
    public ResponseEntity<List<Department>> listDepartment()
    {
        List<Department> departments=new ArrayList<>();
        departments=departmentService.listAllDepartment();
        if(departments.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departments);
    }
    @PostMapping
    public ResponseEntity<Department> newDepartment(@RequestBody Department department)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(department));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.edit(department,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Integer id)
    {
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
