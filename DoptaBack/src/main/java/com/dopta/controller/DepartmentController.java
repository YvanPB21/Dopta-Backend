package com.dopta.controller;

import com.dopta.model.Department;
import com.dopta.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id) {
        Department department = departmentService.getDepartment(id);
        if (department == null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(department));
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> listDepartment() {
        List<Department> departments = new ArrayList<>();
        departments = departmentService.listAllDepartment();
        if (departments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> newDepartment(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(department));
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.edit(department, id));
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
