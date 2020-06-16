package com.dopta.service.impl;

import com.dopta.model.Department;
import com.dopta.repository.DepartmentRepository;
import com.dopta.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department getDepartment(Integer id) {

        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department edit(Department department, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Department> listAll() {

        return departmentRepository.findAll();
    }
}
