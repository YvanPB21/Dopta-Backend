package com.tutorial.crud.controller;

import com.tutorial.crud.model.Pet;
import com.tutorial.crud.model.Sex;
import com.tutorial.crud.resource.pet.PetResource;
import com.tutorial.crud.resource.pet.SavePetResource;
import com.tutorial.crud.resource.sex.SaveSexResource;
import com.tutorial.crud.resource.sex.SexResource;
import com.tutorial.crud.service.SexService;
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
public class SexController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SexService sexService;

    @Operation(summary="Get Sexes", description = "Get all sexes by Pages", tags={"sexes"})
    @GetMapping("/sexes")
    public Page<SexResource> getAllSexes(@Parameter(description="Pageable parameter") Pageable pageable){
        Page<Sex> sexPage=sexService.getAllSexes(pageable);
        List<SexResource> resources=sexPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary="Get Sex by Id", description = "Get a sex by specifying Id", tags={"sexes"})
    @GetMapping("/sexes/{sexId}")
    public SexResource getSexById(
            @Parameter(description="Sex Id")
            @PathVariable(name="sexId") Integer sexId){
        return convertToResource(sexService.getSexById(sexId));
    }

    @PostMapping("/sexes")
    public SexResource createSex(@Valid @RequestBody SaveSexResource resource){
        Sex sex=convertToEntity(resource);
        return convertToResource(sexService.createSex(sex));
    }

    @PutMapping("/sexes/{sexId}")
    public SexResource updateSex(@PathVariable (name="sexId")Integer sexId,
                                 @Valid @RequestBody SaveSexResource resource)
    {
        Sex sex=convertToEntity(resource);
        return convertToResource(sexService.editSex(sex,sexId));
    }
    @DeleteMapping("/sexes/{sexId}")
    public ResponseEntity<?> deleteSex(@PathVariable(name="sexId")Integer sexId){
        return sexService.deleteSexById(sexId);
    }


    private SexResource convertToResource(Sex entity){
        return mapper.map(entity, SexResource.class);
    }

    private Sex convertToEntity(SaveSexResource resource){
        return mapper.map(resource,Sex.class);
    }

}
