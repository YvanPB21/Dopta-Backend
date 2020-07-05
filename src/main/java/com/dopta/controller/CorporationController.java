package com.dopta.controller;

import com.dopta.model.Corporation;
import com.dopta.resource.CorporationResource;
import com.dopta.resource.SaveCorporationResource;
import com.dopta.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://dopta.netlify.app")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CorporationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CorporationService corporationService;

    @GetMapping("/corporations")
    public List<CorporationResource> getAllCorporations(Pageable pageable) {
        List<CorporationResource> corporationResources = corporationService.getAllCorporations(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return corporationResources;
    }

    @GetMapping("/corporations/{userId}")
    public CorporationResource getCorporationByUserId(@PathVariable(name = "userId") Integer userId) {
        return convertToResource(corporationService.getCorporationById(userId));
    }

    @PostMapping("/corporations/{userId}/{districtId}/{locatableId}")
    public CorporationResource createCorporation(@PathVariable(name = "userId") Integer userId,
                                                 @PathVariable(name = "districtId") Integer districtId,
                                                 @PathVariable(name = "locatableId") Integer locatableId,
                                                 @Valid @RequestBody SaveCorporationResource resource) {
        return convertToResource(corporationService.createCorporation(convertToEntity(resource), districtId, locatableId));
    }

    @PutMapping("/corporations/{userId}")
    public CorporationResource updateCorporation(@PathVariable(name = "userId") Integer userId,
                                                 @PathVariable(name = "districtId") Integer districtId,
                                                 @PathVariable(name = "locatableId") Integer locatableId,
                                                 @Valid @RequestBody SaveCorporationResource resource) {
        return convertToResource(corporationService.updateCorporation(userId, districtId, locatableId, convertToEntity(resource)));
    }

    @DeleteMapping("/corporations/{userId}")
    public ResponseEntity<?> deleteCorporation(@PathVariable(name = "userId") Integer userId) {
        return corporationService.deleteCorporation(userId);
    }

    private Corporation convertToEntity(SaveCorporationResource resource) {
        return mapper.map(resource, Corporation.class);
    }

    private CorporationResource convertToResource(Corporation entity) {
        return mapper.map(entity, CorporationResource.class);
    }

}
