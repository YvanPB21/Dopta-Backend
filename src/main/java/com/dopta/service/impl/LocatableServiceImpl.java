package com.dopta.service.impl;

import com.dopta.exception.ResourceNotFoundException;
import com.dopta.model.Locatable;
import com.dopta.repository.LocatableRepository;
import com.dopta.service.LocatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocatableServiceImpl implements LocatableService {

    @Autowired
    private LocatableRepository locatableRepository;

    @Override
    public Locatable getLocatable(Integer id) {

        return locatableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Countries", "Id", id));
    }

    @Override
    @Transactional
    public Locatable save(Locatable locatable) {

        return locatableRepository.save(locatable);
    }

    @Override
    public Optional<Locatable> findById(Integer id) {

        return locatableRepository.findById(id);
    }

    @Override
    public List<Locatable> listAllLocatable() {

        return locatableRepository.findAll();
    }

    @Override
    @Transactional
    public Locatable edit(Locatable locatable, Integer id) {

        return locatableRepository.findById(id).map(locatable1 -> {
            locatable1.setAddress(locatable.getAddress());
            locatable1.setName(locatable.getName());
            return locatableRepository.save(locatable1);
        }).orElseThrow(() -> new ResourceNotFoundException("Localtable", "id", id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        locatableRepository.deleteById(id);
    }
}
