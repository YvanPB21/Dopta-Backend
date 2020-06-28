package com.tutorial.crud.service.impl;


import com.tutorial.crud.model.PromoCode;
import com.tutorial.crud.exception.ResourceNotFoundException;
import com.tutorial.crud.repository.PromoCodeRepository;
import com.tutorial.crud.repository.PromoRepository;
import com.tutorial.crud.repository.UserRepository;
import com.tutorial.crud.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoRepository promoRepository;


    @Override
    public PromoCode getPromoCodeById(Integer promoCodeId) {
        return promoCodeRepository.findById(promoCodeId).orElseThrow(()->new ResourceNotFoundException("Promo Code","Id",promoCodeId));
    }

    @Override
    public Page<PromoCode> getAllPromoCodesByPromoId(Integer promoCodeId, Pageable pageable) {
        return promoCodeRepository.findAllByPromoId(promoCodeId,pageable);
    }

    @Override
    public Page<PromoCode> getAllPromoCodesByUserId(Integer userId, Pageable pageable) {
        return promoCodeRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Page<PromoCode> getAllPromoCodes(Pageable pageable) {
        return promoCodeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public PromoCode createPromoCode(PromoCode promoCode, Integer userId, Integer promoId) {
        PromoCode newPromoCode=new PromoCode();
        newPromoCode.setIsUsed(promoCode.getIsUsed());
        newPromoCode.setUser(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId)));
        newPromoCode.setPromo(promoRepository.findById(promoId).orElseThrow(()->new ResourceNotFoundException("Promo","Id",promoId)));
        return promoCodeRepository.save(newPromoCode);
    }

    @Override
    @Transactional
    public PromoCode editPromoCode(PromoCode promoCode, Integer promoCodeId, Integer userId, Integer promoId) {
        if(!promoRepository.existsById(promoId))
            throw new ResourceNotFoundException("Promo","Id",promoId);
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User","Id",userId);
        return promoCodeRepository.findById(promoCodeId).map(pc->{
            pc.setIsUsed(promoCode.getIsUsed());
            pc.setUser(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId)));
            pc.setPromo(promoRepository.findById(promoId).orElseThrow(()->new ResourceNotFoundException("Promo","Id",promoId)));
            return promoCodeRepository.save(pc);
        }).orElseThrow(()->new ResourceNotFoundException("Promo Code","Id",promoCodeId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> deletePromoCode(Integer promoCodeId) {
        return promoCodeRepository.findById(promoCodeId).map(pc->{
            promoCodeRepository.delete(pc);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Promo Code"));
    }
}
