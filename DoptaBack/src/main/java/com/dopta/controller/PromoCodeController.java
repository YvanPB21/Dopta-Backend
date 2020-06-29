package com.dopta.controller;

import com.dopta.model.PromoCode;
import com.dopta.resource.promocode.PromoCodeResource;
import com.dopta.resource.promocode.SavePromoCodeResource;
import com.dopta.service.PromoCodeService;
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
public class PromoCodeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PromoCodeService promoCodeService;

    @Operation(summary = "Get Promo Codes", description = "Get all promocodes by Pages", tags = {"promocodes"})
    @GetMapping("/promocodes")
    public Page<PromoCodeResource> getAllPromoCodes(@Parameter(description = "Pageable parameter") Pageable pageable) {
        Page<PromoCode> promoCodePage = promoCodeService.getAllPromoCodes(pageable);
        List<PromoCodeResource> resources = promoCodePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get PromoCode by Id", description = "Get a promoCode by specifying Id", tags = {"promocodes"})
    @GetMapping("/promocodes/{promoCodeId}")
    public PromoCodeResource getPromoCodeById(
            @Parameter(description = "PromoCode Id")
            @PathVariable(name = "promoCodeId") Integer promoCodeId) {
        return convertToResource(promoCodeService.getPromoCodeById(promoCodeId));
    }

    @Operation(summary = "Get PromoCodes by Promo Id", description = "Get promoCodes by specifying Promo Id", tags = {"promocodes"})
    @GetMapping("/promocodes/promo/{promoId}")
    public Page<PromoCodeResource> getPromoCodesByPromoId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Promo Id")
            @PathVariable(name = "promoId") Integer promoId) {
        Page<PromoCode> promoCodePage = promoCodeService.getAllPromoCodesByPromoId(promoId, pageable);
        List<PromoCodeResource> resources = promoCodePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get PromoCodes by user Id", description = "Get promoCodes by specifying User Id", tags = {"promocodes"})
    @GetMapping("/promocodes/user/{userId}")
    public Page<PromoCodeResource> getPromoCodesByUserId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "User Id")
            @PathVariable(name = "userId") Integer userId) {
        Page<PromoCode> promoCodePage = promoCodeService.getAllPromoCodesByUserId(userId, pageable);
        List<PromoCodeResource> resources = promoCodePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/promocodes")
    public PromoCodeResource createPromoCode(@Valid @RequestBody SavePromoCodeResource resource, Integer userId, Integer promoId) {
        PromoCode promoCode = convertToEntity(resource);
        return convertToResource(promoCodeService.createPromoCode(promoCode, userId, promoId));
    }

    @PutMapping("/promocodes/{promoCodeId}")
    public PromoCodeResource updatePromoCode(@PathVariable(name = "promoCodeId") Integer promoCodeId,
                                             @Valid @RequestBody SavePromoCodeResource resource,
                                             Integer userId, Integer promoId) {
        PromoCode promoCode = convertToEntity(resource);
        return convertToResource(promoCodeService.editPromoCode(promoCode, promoCodeId, userId, promoId));
    }

    @DeleteMapping("/promocodes/{promoCodeId}")
    public ResponseEntity<?> deletePromoCode(@PathVariable(name = "promoCodeId") Integer promoCodeId) {
        return promoCodeService.deletePromoCode(promoCodeId);
    }


    private PromoCodeResource convertToResource(PromoCode entity) {
        return mapper.map(entity, PromoCodeResource.class);
    }

    private PromoCode convertToEntity(SavePromoCodeResource resource) {
        return mapper.map(resource, PromoCode.class);
    }

}
