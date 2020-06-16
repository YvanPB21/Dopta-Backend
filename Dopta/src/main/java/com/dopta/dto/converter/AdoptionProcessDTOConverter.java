package com.dopta.dto.converter;

import com.dopta.dto.adoptionprocess.AdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.CreateAdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.EditAdoptionProcessDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class AdoptionProcessDTOConverter {
    private final ModelMapper modelMapper;

    @PostConstruct
    public void init(){
        modelMapper.addMappings(new PropertyMap<AdoptionProcess, AdoptionProcessDTO>() {

            @Override
            protected void configure() {
                map().setAdopter(source.getAdopter().getNames().concat(" ").concat(source.getAdopter().getLast_names()));
                map().setPoster(source.getPoster().getNames().concat(" ").concat(source.getAdopter().getLast_names()));
                map().setPet(source.getPet().getName());
            }
        });
    }

    public AdoptionProcessDTO convertToDto(AdoptionProcess adoptionProcess)
    {
        return modelMapper.map(adoptionProcess, AdoptionProcessDTO.class);
    }

    public AdoptionProcess convertToEntity(CreateAdoptionProcessDTO adoptionProcessDTO)
    {
        return modelMapper.map(adoptionProcessDTO, AdoptionProcess.class);
    }

    public AdoptionProcess convertToEntity(EditAdoptionProcessDTO adoptionProcessDTO)
    {
        return modelMapper.map(adoptionProcessDTO, AdoptionProcess.class);
    }
}
