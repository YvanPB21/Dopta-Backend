package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Country;
import com.tutorial.crud.model.Department;
import com.tutorial.crud.repository.CountryRepository;
import com.tutorial.crud.repository.DepartmentRepository;
import com.tutorial.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department getDepartment(Integer id) {

        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "Id", id));
    }
    @Override
    @Transactional
    public Department save(Department department) {

        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> findById(Integer id) {

        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> listAllDepartment() {

        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department edit(Department department, Integer id) {

        return departmentRepository.findById(id).map(department1 -> {
            department1.setName(department.getName());
            return departmentRepository.save(department1);
        }).orElseThrow(() -> new ResourceNotFoundException("Department","id",id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

}
