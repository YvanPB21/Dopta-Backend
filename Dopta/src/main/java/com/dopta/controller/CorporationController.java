package com.dopta.controller;

import com.dopta.model.Corporation;
import com.dopta.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corporations")
public class CorporationController {
    @Autowired
    private CorporationService corporationService;
    @GetMapping
    public ResponseEntity<List<Corporation>> listCorporation()
    {
        List<Corporation> corporations=new ArrayList<>();
        corporations=corporationService.listAllCorporations();
        if(corporations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(corporations);
    }
    @GetMapping("{id}")
    public ResponseEntity<Corporation>getById(@PathVariable Integer id)
    {
        Corporation corporation=corporationService.getCorporation(id);
        if(corporation==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(corporation));
    }

    @PostMapping
    public ResponseEntity<Corporation> newCorporation(@RequestBody Corporation corporation)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(corporationService.save(corporation));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Corporation>updateCorporation(@RequestBody Corporation corporation, @PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(corporationService.edit(corporation,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Corporation> deleteCorporation(@PathVariable Integer id)
    {
        corporationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
