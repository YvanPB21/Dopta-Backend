package com.dopta.service.impl;

import com.dopta.model.Corporation;
import com.dopta.model.Promo;
import com.dopta.model.User;
import com.dopta.repository.PromoRepository;
import com.dopta.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    private PromoRepository promoRepository;

    @Override
    public Promo getPromo(Integer id) {
        return promoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Promo save(Promo promo) {
        return promoRepository.save(promo);
    }

    @Override
    public Optional<Promo> findById(Integer id) {
        return promoRepository.findById(id);
    }

    @Override
    public List<Promo> listAllPromos() {
        return promoRepository.findAll();
    }

    @Override
    @Transactional
    public Promo edit(Promo promo, Integer id) {
        return promoRepository.findById(id).map(p->
        {
            p.setDescription(promo.getDescription());
            p.setImageUrl(promo.getImageUrl());
            p.setName(promo.getName());
            p.setCorporation(promo.getCorporation());
            return promoRepository.save(p);
        }).orElse(null);
    }

    @Override
    public List<Promo> findByCorporation(User corporation) {
        return promoRepository.findByCorporation(corporation);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        promoRepository.deleteById(id);
    }
}
