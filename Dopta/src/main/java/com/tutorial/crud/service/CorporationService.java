package com.tutorial.crud.service;

import com.tutorial.crud.model.Corporation;

import java.util.List;
import java.util.Optional;

public interface CorporationService {
    Corporation getCorporation(Integer id);
    Corporation save(Corporation Corporation);
    Optional<Corporation> findById(Integer id);
    List<Corporation> listAllCorporations();
    Corporation edit(Corporation Corporation, Integer id);
    void deleteById(Integer id);
}