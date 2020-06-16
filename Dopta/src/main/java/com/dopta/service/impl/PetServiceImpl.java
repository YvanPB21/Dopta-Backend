package com.dopta.service.Impl;

import com.dopta.dto.converter.PetDTOConverter;
import com.dopta.dto.pet.CreatePetDTO;
import com.dopta.dto.pet.EditPetDTO;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.Pet;
import com.dopta.model.Species;
import com.dopta.model.User;
import com.dopta.repository.PetRepository;
import com.dopta.repository.UserRepository;
import com.dopta.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetDTOConverter petDTOConverter;

   @Override
   @Transactional
    public Pet save(CreatePetDTO petDTO) {
        Pet pet = petDTOConverter.convertToEntity(petDTO);
        return petRepository.save(pet);
    }

    @Override
    public Optional<PetDTO> findById(Integer id) {
        return petRepository.findById(id).map(petDTOConverter::convertToDto);
    }

    @Override
    public List<PetDTO> listAllPets() {
        List<PetDTO> petsDTO = petRepository.findAll().stream().map(petDTOConverter::convertToDto)
                .collect(Collectors.toList());
        return petsDTO;
    }

    @Override
    @Transactional
    public Pet edit(EditPetDTO petDTO, Integer id) {
        return petRepository.findById(id).map(p->{
            p.setName(petDTO.getName());
            p.setImage_url(petDTO.getImage_url());
            p.setIs_adopted(petDTO.getIs_adopted());
            return petRepository.save(p);
        }).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Integer id)
    {
        petRepository.deleteById(id);
    }
}