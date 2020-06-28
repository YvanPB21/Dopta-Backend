package com.tutorial.crud.controller;


import com.tutorial.crud.model.Size;
import com.tutorial.crud.resource.size.SaveSizeResource;
import com.tutorial.crud.resource.size.SizeResource;
import com.tutorial.crud.service.SizeService;
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
public class SizeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SizeService sizeService;

    @Operation(summary="Get Sizes", description = "Get all sizes by Pages", tags={"sizes"})
    @GetMapping("/sizes")
    public Page<SizeResource> getAllSize(@Parameter(description="Pageable parameter") Pageable pageable){
        Page<Size> sizePage=sizeService.getAllSizes(pageable);
        List<SizeResource> resources=sizePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary="Get Size by Id", description = "Get a size by specifying Id", tags={"sizes"})
    @GetMapping("/sizes/{sizeId}")
    public SizeResource getSizeById(
            @Parameter(description="Size Id")
            @PathVariable(name="sizeId") Integer sizeId){
        return convertToResource(sizeService.getSizeById(sizeId));
    }

    @PostMapping("/sizes")
    public SizeResource createSize(@Valid @RequestBody SaveSizeResource resource){
        Size size=convertToEntity(resource);
        return convertToResource(sizeService.createSize(size));
    }

    @PutMapping("/sizes/{sizeId}")
    public SizeResource updateSize(@PathVariable (name="sizeId")Integer sizeId,
                                         @Valid @RequestBody SaveSizeResource resource)
    {
        Size size=convertToEntity(resource);
        return convertToResource(sizeService.editSize(size,sizeId));
    }
    @DeleteMapping("/sizes/{sizeId}")
    public ResponseEntity<?> deleteSize(@PathVariable(name="sizeId")Integer sizeId){
        return sizeService.deleteSizeById(sizeId);
    }


    private SizeResource convertToResource(Size entity){
        return mapper.map(entity, SizeResource.class);
    }

    private Size convertToEntity(SaveSizeResource resource){
        return mapper.map(resource,Size.class);
    }

}