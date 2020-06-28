package com.tutorial.crud.controller;

import com.tutorial.crud.model.Corporation;
import com.tutorial.crud.model.Corporation;
import com.tutorial.crud.resource.CorporationResource;
import com.tutorial.crud.resource.SaveCorporationResource;
import com.tutorial.crud.service.CorporationService;
import com.tutorial.crud.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CorporationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CorporationService corporationService;

    @GetMapping("/corporations")
    public Page<CorporationResource> getAllCorporations(Pageable pageable){
        List<CorporationResource> corporationResources = corporationService.getAllCorporations(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(corporationResources,pageable,corporationResources.size());
    }

    @GetMapping("/corporations/{userId}")
    public CorporationResource getCorporationByUserId(@PathVariable(name = "userId") Integer userId){
        return convertToResource(corporationService.getCorporationByUserId(userId));
    }

    @PostMapping("/corporations/{userId}")
    public CorporationResource createCorporation(@PathVariable(name = "userId") Integer userId,
                                                 @Valid @RequestBody SaveCorporationResource resource){
        return convertToResource(corporationService.createCorporation(userId, convertToEntity(resource)));
    }

    @PutMapping("/corporations/{userId}")
    public CorporationResource updateCorporation(@PathVariable(name = "userId") Integer userId,
                                       @Valid @RequestBody SaveCorporationResource resource){
        return convertToResource(corporationService.updateCorporation(userId, convertToEntity(resource)));
    }

    @DeleteMapping("/corporations/{userId}")
    public ResponseEntity<?> deleteCorporation(@PathVariable(name = "userId") Integer userId){
        return corporationService.deleteCorporation(userId);
    }

    private Corporation convertToEntity(SaveCorporationResource resource) {
        return mapper.map(resource, Corporation.class);
    }

    private CorporationResource convertToResource(Corporation entity) {
        return mapper.map(entity, CorporationResource.class);
    }

}
