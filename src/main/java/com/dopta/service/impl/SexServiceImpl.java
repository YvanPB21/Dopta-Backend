package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Sex;
import com.dopta.repository.SexRepository;
import com.dopta.service.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SexServiceImpl implements SexService {
    @Autowired
    SexRepository sexRepository;

    @Override
    public Sex getSexById(Integer sexId) {
        return sexRepository.findById(sexId).orElseThrow(() -> new ResourceNotFoundException("Sex", "Id", sexId));
    }

    @Override
    public Page<Sex> getAllSexes(Pageable pageable) {
        return sexRepository.findAll(pageable);
    }

    @Override
    public Sex createSex(Sex sex) {
        Sex newSex = new Sex();
        newSex.setName(sex.getName());
        return sexRepository.save(newSex);
    }

    @Override
    public Sex editSex(Sex sexRequest, Integer sexId) {
        if (!sexRepository.existsById(sexId))
            throw new ResourceNotFoundException("Sex", "Id", sexId);
        return sexRepository.findById(sexId).map(sex ->
        {
            sex.setName(sexRequest.getName());
            return sexRepository.save(sex);
        }).orElseThrow(() -> new ResourceNotFoundException("Sex", "Id", sexId));
    }

    @Override
    public ResponseEntity<?> deleteSexById(Integer sexId) {
        return sexRepository.findById(sexId).map(sex -> {
            sexRepository.delete(sex);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Sex"));
    }
}
