package com.dopta.dto.converter;

import com.dopta.dto.pet.CreatePetDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.Pet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class PetDTOConverter {
    private final ModelMapper modelMapper;

    //mapExplicitly
    @PostConstruct
    public void init(){
        modelMapper.addMappings(new PropertyMap<Pet, PetDTO>() {

            @Override
            protected void configure() {
                map().setSex(source.getSex().getName());
                map().setSize(source.getSize().getName());
                map().setSpecies(source.getSpecies().getName());
            }
        });
    }

    //mapAutomatic
    public PetDTO convertToDto(Pet pet) {
        return modelMapper.map(pet, PetDTO.class);

    }

    public Pet convertToEntity(CreatePetDTO productDto) {
        return modelMapper.map(productDto, Pet.class);

    }


}