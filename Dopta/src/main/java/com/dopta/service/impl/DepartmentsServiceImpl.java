package com.dopta.service.impl;

import com.dopta.model.Departments;
import com.dopta.model.Districts;
import com.dopta.repository.DepartmentsRepository;
import com.dopta.repository.DistrictsRepository;
import com.dopta.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    @Autowired
    private DepartmentsRepository departmentsRepository;
    @Override
    public Departments getDepartments(Integer id) {

        return departmentsRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Departments save(Departments departments) {
        return departmentsRepository.save(departments);
    }

    @Override
    public Departments edit(Departments departments, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Departments> listAll() {

        return departmentsRepository.findAll();
    }
}
