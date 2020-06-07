package com.dopta.service.impl;

import com.dopta.model.Locatable;
import com.dopta.model.User;
import com.dopta.repository.LocatableRepository;
import com.dopta.repository.UserRepository;
import com.dopta.service.LocatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class LocatableServiceImpl implements LocatableService {
    @Autowired
    private LocatableRepository locatableRepository;
    @Override
    public Locatable getLocatable(Integer id) {

        return locatableRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Locatable save(Locatable locatable) {
        return locatableRepository.save(locatable);
    }

    @Override
    public Locatable edit(Locatable locatable, Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    public List<Locatable> listAll() {
        return locatableRepository.findAll();
    }
}
