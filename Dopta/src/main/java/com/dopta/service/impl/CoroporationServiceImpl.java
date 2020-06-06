package com.dopta.service.impl;

import com.dopta.model.Corporation;
import com.dopta.model.Pet;
import com.dopta.repository.CorporationRepository;
import com.dopta.service.CorporationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CoroporationServiceImpl implements CorporationService {

    @Autowired
    private CorporationRepository corporationRepository;

    @Override
    public Corporation getCorporation(Integer id) {
        return corporationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Corporation save(Corporation corporation) {
        return corporationRepository.save(corporation);
    }

    @Override
    public Optional<Corporation> findById(Integer id) {
        return corporationRepository.findById(id);
    }

    @Override
    public List<Corporation> listAllCorporations() {
        return corporationRepository.findAll();
    }

    @Override
    @Transactional
    public Corporation edit(Corporation corporation, Integer id) {
        return corporationRepository.findById(id).map(c->{
            c.setName(corporation.getName());
            c.setRuc(corporation.getRuc());
            c.setEmail_address(corporation.getEmail_address());
            c.setUsername(corporation.getUsername());
            c.setPassword(corporation.getPassword());
            return corporationRepository.save(c);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        corporationRepository.deleteById(id);
    }
}