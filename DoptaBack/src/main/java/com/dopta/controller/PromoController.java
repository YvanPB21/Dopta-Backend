package com.dopta.controller;

import com.dopta.model.Promo;
import com.dopta.resource.promo.PromoResource;
import com.dopta.resource.promo.SavePromoResource;
import com.dopta.service.PromoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class PromoController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PromoService promoService;

    @Operation(summary = "Get Promos", description = "Get all promos by Pages", tags = {"promos"})
    @GetMapping("/promos")
    public List<PromoResource> getAllPromos(@Parameter(description = "Pageable parameter") Pageable pageable) {
        Page<Promo> promoPage = promoService.getAllPromos(pageable);
        List<PromoResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Promo by Id", description = "Get a promo by specifying Id", tags = {"promos"})
    @GetMapping("/promos/{promoId}")
    public PromoResource getPromoById(
            @Parameter(description = "Promo Id")
            @PathVariable(name = "promoId") Integer promoId) {
        return convertToResource(promoService.getPromoById(promoId));
    }

    @Operation(summary = "Get Promos by corporation Id", description = "Get promos by specifying Corporation Id", tags = {"promos"})
    @GetMapping("/promos/corporation/{corporationId}")
    public List<PromoResource> getPromosByCorporationId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Corporation Id")
            @PathVariable(name = "corporationId") Integer corporationId) {
        Page<Promo> promoPage = promoService.getAllPromosByCorporationId(corporationId, pageable);
        List<PromoResource> resources = promoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @PostMapping("/promos")
    public PromoResource createPromo(@Valid @RequestBody SavePromoResource resource, Integer corporationId) {
        Promo promo = convertToEntity(resource);
        return convertToResource(promoService.createPromo(promo, corporationId));
    }

    @PutMapping("/promos/{promoId}")
    public PromoResource updatePromo(@PathVariable(name = "promoId") Integer promoId,
                                     @Valid @RequestBody SavePromoResource resource,
                                     Integer corporationId) {
        Promo promo = convertToEntity(resource);
        return convertToResource(promoService.editPromo(promo, promoId, corporationId));
    }

    @DeleteMapping("/promos/{promoId}")
    public ResponseEntity<?> deletePromo(@PathVariable(name = "promoId") Integer promoId) {
        return promoService.deletePromo(promoId);
    }

    private PromoResource convertToResource(Promo entity) {
        return mapper.map(entity, PromoResource.class);
    }

    private Promo convertToEntity(SavePromoResource resource) {
        return mapper.map(resource, Promo.class);
    }


}
