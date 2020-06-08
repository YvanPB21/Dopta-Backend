package com.dopta.service.impl;

import com.dopta.model.PromoCode;
import com.dopta.repository.PromoCodeRepository;
import com.dopta.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Override
    public PromoCode getPromoCode(Integer id) {
        return promoCodeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PromoCode save(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    @Override
    public Optional<PromoCode> findById(Integer id) {
        return promoCodeRepository.findById(id);
    }

    @Override
    public List<PromoCode> listAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

    @Override
    @Transactional
    public PromoCode edit(PromoCode promoCode, Integer id) {
        return promoCodeRepository.findById(id).map(pc->{
            pc.setIsUsed(promoCode.getIsUsed());
            pc.setPromo(promoCode.getPromo());
            pc.setUser(promoCode.getUser());
            return promoCodeRepository.save(pc);
        }).orElse(null);
    }

    @Override
    public List<PromoCode> findByIsUsed(Byte isUsed) {
        return promoCodeRepository.findByIsUsed(isUsed);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        promoCodeRepository.deleteById(id);
    }
}
