package com.dopta.controller;

import com.dopta.model.Pet;
import com.dopta.resource.pet.PetResource;
import com.dopta.resource.pet.SavePetResource;
import com.dopta.service.PetService;
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
public class PetController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PetService petService;

    @Operation(summary = "Get Pets", description = "Get all pets by Pages", tags = {"pets"})
    @GetMapping("/pets")
    public List<PetResource> getAllPets(@Parameter(description = "Pageable parameter") Pageable pageable) {
        Page<Pet> petPage = petService.getAllPets(pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Pet by Id", description = "Get a pet by specifying Id", tags = {"pets"})
    @GetMapping("/pets/{petId}")
    public PetResource getPetById(
            @Parameter(description = "Pet Id")
            @PathVariable(name = "petId") Integer petId) {
        return convertToResource(petService.getPetById(petId));
    }

    @Operation(summary = "Get Pets by sex Id", description = "Get pets by specifying Sex Id", tags = {"pets"})
    @GetMapping("/pets/sex/{sexId}")
    public List<PetResource> getPetsBySexId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Sex Id")
            @PathVariable(name = "sexId") Integer sexId) {
        Page<Pet> petPage = petService.getPetsBySexId(sexId, pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Pets by size Id", description = "Get pets by specifying Size Id", tags = {"pets"})
    @GetMapping("/pets/size/{sizeId}")
    public List<PetResource> getPetsBySizeId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Size Id")
            @PathVariable(name = "sizeId") Integer sizeId) {
        Page<Pet> petPage = petService.getPetsBySizeId(sizeId, pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Pets by species Id", description = "Get pets by specifying Species Id", tags = {"pets"})
    @GetMapping("/pets/species/{speciesId}")
    public List<PetResource> getPetsBySpeciesId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Species Id")
            @PathVariable(name = "speciesId") Integer speciesId) {
        Page<Pet> petPage = petService.getPetsBySpeciesId(speciesId, pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Pets by size Id and sex Id", description = "Get pets by specifying Size Id and Sex Id", tags = {"pets"})
    @GetMapping(value = {"/pets/size/{sizeId}/sex/{sexId}", "/pets/sex/{sexId}/size/{sizeId}"})
    public List<PetResource> getPetsBySizeIdAndSexId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Size Id") @PathVariable(name = "sizeId") Integer sizeId,
            @Parameter(description = "Sex Id") @PathVariable(name = "sexId") Integer sexId
    ) {
        Page<Pet> petPage = petService.getPetsBySizeIdAndSexId(sizeId, sexId, pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Pets by species Id and sex Id", description = "Get pets by specifying Species Id and Sex Id", tags = {"pets"})
    @GetMapping(value = {"/pets/species/{speciesId}/sex/{sexId}", "/pets/sex/{sexId}/species/{speciesId}"})
    public List<PetResource> getPetsBySpeciesIdAndSexId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Species Id") @PathVariable(name = "speciesId") Integer speciesId,
            @Parameter(description = "Sex Id") @PathVariable(name = "sexId") Integer sexId
    ) {
        Page<Pet> petPage = petService.getPetsBySpeciesIdAndSexId(speciesId, sexId, pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Pets by species Id and size Id", description = "Get pets by specifying Species Id and Size Id", tags = {"pets"})
    @GetMapping(value = {"/pets/species/{speciesId}/size/{sizeId}", "/pets/size/{sizeId}/species/{speciesId}"})
    public List<PetResource> getPetsBySpeciesIdAndSizeId(
            @Parameter(description = "Pageable parameter") Pageable pageable,
            @Parameter(description = "Species Id") @PathVariable(name = "speciesId") Integer speciesId,
            @Parameter(description = "Size Id") @PathVariable(name = "sizeId") Integer sizeId
    ) {
        Page<Pet> petPage = petService.getPetsBySpeciesIdAndSizeId(speciesId, sizeId, pageable);
        List<PetResource> resources = petPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @PostMapping("/pets")
    public PetResource createPet(@Valid @RequestBody SavePetResource resource, Integer speciesId, Integer sexId, Integer sizeId) {
        Pet pet = convertToEntity(resource);
        return convertToResource(petService.createPet(pet, sizeId, speciesId, sexId));
    }

    @PutMapping("/pets/{petId}")
    public PetResource updatePet(@PathVariable(name = "petId") Integer petId,
                                 @Valid @RequestBody SavePetResource resource,
                                 Integer speciesId, Integer sexId, Integer sizeId) {
        Pet pet = convertToEntity(resource);
        return convertToResource(petService.editPet(pet, petId, sizeId, speciesId, sexId));
    }

    @DeleteMapping("/pets/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable(name = "petId") Integer petId) {
        return petService.deletePetById(petId);
    }

    private PetResource convertToResource(Pet entity) {
        return mapper.map(entity, PetResource.class);
    }

    private Pet convertToEntity(SavePetResource resource) {
        return mapper.map(resource, Pet.class);
    }

}
