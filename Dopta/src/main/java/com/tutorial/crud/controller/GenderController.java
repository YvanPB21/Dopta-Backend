package com.tutorial.crud.controller;


import com.tutorial.crud.model.Gender;
import com.tutorial.crud.resource.GenderResource;
import com.tutorial.crud.resource.SaveGenderResource;
import com.tutorial.crud.service.GenderService;
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
@RequestMapping("/api")
public class GenderController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private GenderService genderService;

    @GetMapping("/genders")
    public Page<GenderResource> getAllGenders(Pageable pageable){
        List<GenderResource> genderResources = genderService.getAllGenders(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(genderResources,pageable,genderResources.size());
    }

    @GetMapping("/genders/{genderId}")
    public GenderResource getGenderByUserId(@PathVariable(name = "genderId") Integer genderId){
        return convertToResource(genderService.getGenderById(genderId));
    }

    @PostMapping("/genders")
    public GenderResource createGender(@Valid @RequestBody SaveGenderResource resource){
        return convertToResource(genderService.createGender(convertToEntity(resource)));
    }

    @PutMapping("/genders/{genderId}")
    public GenderResource updateGender(@PathVariable(name = "genderId") Integer genderId,
                                                 @Valid @RequestBody SaveGenderResource resource){
        return convertToResource(genderService.updateGender(genderId, convertToEntity(resource)));
    }

    @DeleteMapping("/genders/{genderId}")
    public ResponseEntity<?> deleteGender(@PathVariable(name = "genderId") Integer genderId){
        return genderService.deleteGender(genderId);
    }

    private Gender convertToEntity(SaveGenderResource resource) {
        return mapper.map(resource, Gender.class);
    }

    private GenderResource convertToResource(Gender entity) {
        return mapper.map(entity, GenderResource.class);
    }

}
