package com.tutorial.crud.service;

import com.tutorial.crud.model.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SexService {
    Sex getSexById(Integer sexId);
    Page<Sex> getAllSexes(Integer sexId, Pageable pageable);
    Sex createSex(Sex sex);
    Sex editSex(Sex sexRequest, Integer sexId);
    ResponseEntity<?> deleteSexById(Integer sexId);
}
