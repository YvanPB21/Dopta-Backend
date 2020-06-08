package com.dopta.controller;

import com.dopta.model.Corporation;
import com.dopta.model.Promo;
import com.dopta.model.User;
import com.dopta.service.PromoService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/promos")
public class PromoController {
    @Autowired
    private PromoService promoService;

    @GetMapping
    public ResponseEntity<List<Promo>> listPromo(@RequestParam(name="CorporationId", required=false)Integer corporationId)
    {
        List<Promo> promos=new ArrayList<>();
        if(null==corporationId){
            promos=promoService.listAllPromos();
            if (promos.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
        }else
        {
            promos=promoService.findByCorporation(User.builder().id(corporationId).build());
        }
        return ResponseEntity.ok(promos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promo>getById(@PathVariable Integer id)
    {
        Promo promo=promoService.getPromo(id);
        if(promo==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(promo));
    }
    @PostMapping
    public ResponseEntity<Promo> newPromo(@RequestBody Promo promo)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(promoService.save(promo));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Promo>updatePromo(@RequestBody Promo promo, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(promoService.edit(promo,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Promo> deletePromo(@PathVariable Integer id)
    {
        promoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
