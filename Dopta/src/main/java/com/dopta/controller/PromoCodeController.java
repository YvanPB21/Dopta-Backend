package com.dopta.controller;

import com.dopta.model.PromoCode;
import com.dopta.service.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/promocodes")
public class PromoCodeController {
    @Autowired
    private PromoCodeService promoCodeService;
    @GetMapping
    public ResponseEntity<List<PromoCode>> listPromoCodes(@RequestParam(name="IsUsed",required = false)Byte isUsed)
    {
        List<PromoCode> promoCodes=new ArrayList<>();
        if (null==isUsed){
            promoCodes=promoCodeService.listAllPromoCodes();
            if(promoCodes.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
        }else
        {
            promoCodes=promoCodeService.findByIsUsed(isUsed);
        }
        return ResponseEntity.ok(promoCodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromoCode>getById(@PathVariable Integer id)
    {
        PromoCode promoCode=promoCodeService.getPromoCode(id);
        if(promoCode==null)
            return ResponseEntity.notFound().build();
        else return (ResponseEntity.ok(promoCode));
    }

    @PostMapping
    public ResponseEntity<PromoCode> newPromoCode(@RequestBody PromoCode promoCode)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(promoCodeService.save(promoCode));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromoCode>updatePromoCode(@RequestBody PromoCode promoCode, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(promoCodeService.edit(promoCode,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PromoCode>deletePromoCode(@PathVariable Integer id)
    {
        promoCodeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
