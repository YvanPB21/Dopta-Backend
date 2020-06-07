package com.dopta.service;

import com.dopta.model.Locatable;
import com.dopta.model.User;

import java.util.List;

public interface LocatableService {
    Locatable getLocatable(Integer id); //ok
    List<Locatable> listAll(); //ok
    Locatable save(Locatable locatable); //ok
    Locatable edit(Locatable locatable, Integer id); //ok
    void deleteById(Integer id);
}
