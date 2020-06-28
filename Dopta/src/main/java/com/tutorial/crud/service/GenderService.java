package com.tutorial.crud.service;

import com.tutorial.crud.model.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GenderService {
    Gender getGenderById(Integer genderId);
    Page<Gender> getAllGenders(Pageable pageable);
    Gender createGender(Gender gender);
    Gender updateGender(Integer genderId, Gender genderDetails);
    ResponseEntity<?> deleteGender(Integer genderId);
}
