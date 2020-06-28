package com.tutorial.crud.service.impl;

import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.model.Gender;
import com.tutorial.crud.repository.GenderRepository;
import com.tutorial.crud.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public Gender getGenderById(Integer genderId) {
        return genderRepository.findById(genderId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Gender not found with genderId " + genderId
                ));
    }

    @Override
    public Page<Gender> getAllGenders(Pageable pageable) {
        return genderRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Gender createGender(Gender gender) {
        return genderRepository.save(gender);
    }


    @Override
    @Transactional
    public Gender updateGender(Integer genderId, Gender genderDetails) {
        return genderRepository.findById(genderId).map(g->{
            g.setName(genderDetails.getName());
            return genderRepository.save(g);
        }).orElseThrow(()->new ResourceNotFoundException("Gender","Id",genderId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteGender(Integer genderId) {
        return genderRepository.findById(genderId).map(g ->{
            genderRepository.delete(g);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Gender","Id",genderId));
    }
}
