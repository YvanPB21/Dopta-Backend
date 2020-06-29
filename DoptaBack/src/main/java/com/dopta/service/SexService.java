package com.dopta.service;

import com.dopta.model.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SexService {
    Sex getSexById(Integer sexId);

    Page<Sex> getAllSexes(Pageable pageable);

    Sex createSex(Sex sex);

    Sex editSex(Sex sexRequest, Integer sexId);

    ResponseEntity<?> deleteSexById(Integer sexId);
}
