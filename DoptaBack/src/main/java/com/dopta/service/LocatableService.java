package com.dopta.service;

import com.dopta.model.Locatable;

import java.util.List;
import java.util.Optional;

public interface LocatableService {
    Locatable getLocatable(Integer id); //ok

    Locatable save(Locatable locatable); //ok

    Optional<Locatable> findById(Integer id);

    List<Locatable> listAllLocatable(); //ok

    Locatable edit(Locatable locatable, Integer id);

    void deleteById(Integer id);

}
