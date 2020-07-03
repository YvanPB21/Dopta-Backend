package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Department;
import com.dopta.repository.DepartmentRepository;
import com.dopta.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
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
        }).orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

}
