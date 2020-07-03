package com.dopta.controller;

import com.dopta.model.AdoptionProcess;
import com.dopta.resource.adoptionprocess.AdoptionProcessResource;
import com.dopta.resource.adoptionprocess.SaveAdoptionProcessResource;
import com.dopta.service.AdoptionProcessService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
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
@RequestMapping("/api")
public class AdoptionProcessController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdoptionProcessService adoptionProcessService;

    @Operation(summary = "Get Adoptions", description = "Get all adoptions by Pages", tags = {"adoptions"})
    @GetMapping("/adoptions")
    public List<AdoptionProcessResource> getAllAdoptionProcesses(Pageable pageable) {
        Page<AdoptionProcess> adoptionProcessPage = adoptionProcessService.getAllAdoptionProcesses(pageable);
        List<AdoptionProcessResource> resources = adoptionProcessPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @Operation(summary = "Get Adoptions by Id", description = "Get an adoption by specifying Id", tags = {"adoptions"})
    @GetMapping("/adoptions/{id}")
    public AdoptionProcessResource getAdoptionProcessById(@PathVariable(name = "id") Integer adoptionProcessId) {
        return convertToResource(adoptionProcessService.getAdoptionProcessById(adoptionProcessId));
    }

    @Operation(summary = "Get Adoptions by Pet Id", description = "Get an adoption by specyfing the Id of the associated Pet", tags = {"adoptions"})
    @GetMapping("/pets/{petId}/adoptions")
    public List<AdoptionProcessResource> getAllAdoptionProcessesByPetId(@PathVariable(name = "petId") Integer petId, Pageable pageable) {
        Page<AdoptionProcess> adoptionProcessPage = adoptionProcessService.getAdoptionProcessByPetId(petId, pageable);
        List<AdoptionProcessResource> resources = adoptionProcessPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }
    @Operation(summary = "Get Adoptions by Poster Id", description = "Get adoptions by specyfing the Id of the associated Posters", tags = {"adoptions"})
    @GetMapping("/pets/{posterId}/posters")
    public List<AdoptionProcessResource> getAllAdoptionProcessesByPosterId(@PathVariable(name = "posterId") Integer posterId, Pageable pageable) {
        Page<AdoptionProcess> adoptionProcessPage = adoptionProcessService.getAdoptionProcessByPosterId(posterId, pageable);
        List<AdoptionProcessResource> resources = adoptionProcessPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return resources;
    }

    @PostMapping("/adoptions")
    public AdoptionProcessResource createAdoptionProcess(@Valid @RequestBody SaveAdoptionProcessResource resource, Integer petId, Integer posterId, Integer adopterId) {
        AdoptionProcess adoptionProcess = convertToEntity(resource);
        return convertToResource(adoptionProcessService.createAdoptionProcess(adoptionProcess, petId, posterId, adopterId));
    }

    @PutMapping("/adoptions/{adoptionProcessId}")
    public AdoptionProcessResource updateAdoptionProcess(@PathVariable(name = "adoptionProcessId") Integer adoptionProcessId,
                                                         @Valid @RequestBody SaveAdoptionProcessResource resource,
                                                         Integer petId, Integer posterId, Integer adopterId) {
        AdoptionProcess adoptionProcess = convertToEntity(resource);
        return convertToResource(adoptionProcessService.editAdoptionProcess(adoptionProcess, adoptionProcessId, petId, posterId, adopterId));
    }

    @DeleteMapping("/adoption/{adoptionProcessId}")
    public ResponseEntity<?> deleteAdoptionProcess(@PathVariable(name = "adoptionProcessId") Integer adoptionProcessId) {
        return adoptionProcessService.deleteAdoptionProcessById(adoptionProcessId);
    }

    private AdoptionProcessResource convertToResource(AdoptionProcess entity) {
        return mapper.map(entity, AdoptionProcessResource.class);
    }

    private AdoptionProcess convertToEntity(SaveAdoptionProcessResource resource) {
        return mapper.map(resource, AdoptionProcess.class);
    }

}
