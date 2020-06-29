package com.dopta.controller;


import com.dopta.model.Species;
import com.dopta.resource.species.SaveSpeciesResource;
import com.dopta.resource.species.SpeciesResource;
import com.dopta.service.SpeciesService;
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
public class SpeciesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SpeciesService speciesService;

    @Operation(summary = "Get Species", description = "Get all species by Pages", tags = {"species"})
    @GetMapping("/species")
    public Page<SpeciesResource> getAllSpecies(@Parameter(description = "Pageable parameter") Pageable pageable) {
        Page<Species> speciesPage = speciesService.getAllSpecies(pageable);
        List<SpeciesResource> resources = speciesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Species by Id", description = "Get a species by specifying Id", tags = {"species"})
    @GetMapping("/species/{speciesId}")
    public SpeciesResource getSpeciesById(
            @Parameter(description = "Species Id")
            @PathVariable(name = "speciesId") Integer speciesId) {
        return convertToResource(speciesService.getSpeciesById(speciesId));
    }

    @PostMapping("/species")
    public SpeciesResource createSpecies(@Valid @RequestBody SaveSpeciesResource resource) {
        Species species = convertToEntity(resource);
        return convertToResource(speciesService.createSpecies(species));
    }

    @PutMapping("/species/{speciesId}")
    public SpeciesResource updateSpecies(@PathVariable(name = "speciesId") Integer speciesId,
                                         @Valid @RequestBody SaveSpeciesResource resource) {
        Species species = convertToEntity(resource);
        return convertToResource(speciesService.editSpecies(species, speciesId));
    }

    @DeleteMapping("/species/{speciesId}")
    public ResponseEntity<?> deleteSpecies(@PathVariable(name = "speciesId") Integer speciesId) {
        return speciesService.deleteSpeciesById(speciesId);
    }


    private SpeciesResource convertToResource(Species entity) {
        return mapper.map(entity, SpeciesResource.class);
    }

    private Species convertToEntity(SaveSpeciesResource resource) {
        return mapper.map(resource, Species.class);
    }

}
