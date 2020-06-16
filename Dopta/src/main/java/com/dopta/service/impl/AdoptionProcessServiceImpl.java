package com.dopta.service.Impl;

import com.dopta.dto.adoptionprocess.AdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.CreateAdoptionProcessDTO;
import com.dopta.dto.adoptionprocess.EditAdoptionProcessDTO;
import com.dopta.dto.converter.AdoptionProcessDTOConverter;
import com.dopta.dto.pet.PetDTO;
import com.dopta.model.AdoptionProcess;
import com.dopta.model.Pet;
import com.dopta.model.User;
import com.dopta.repository.AdoptionProcessRepository;
import com.dopta.service.AdoptionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionProcessServiceImpl implements AdoptionProcessService {

    @Autowired
    private AdoptionProcessRepository adoptionProcessRepository;
    @Autowired
    private AdoptionProcessDTOConverter adoptionProcessDTOConverter;


    @Override
    public AdoptionProcess getAdoption(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public AdoptionProcess save(CreateAdoptionProcessDTO adoptionProcessDTO) {
        AdoptionProcess adoptionProcess=adoptionProcessDTOConverter.convertToEntity(adoptionProcessDTO);
        return adoptionProcessRepository.save(adoptionProcess);
    }

    @Override
    public Optional<AdoptionProcessDTO> findById(Integer id) {
        return adoptionProcessRepository.findById(id).map(adoptionProcessDTOConverter::convertToDto);
    }

    @Override
    public List<AdoptionProcessDTO> listAllAdoptions() {
        return adoptionProcessRepository.findAll().stream().map(adoptionProcessDTOConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdoptionProcess edit(EditAdoptionProcessDTO adoptionProcessDTO, Integer id) {
        AdoptionProcess adoptionProcess=adoptionProcessDTOConverter.convertToEntity(adoptionProcessDTO);
        return adoptionProcessRepository.findById(id).map(a->{
            a.setAdopter(adoptionProcess.getAdopter());
            a.setDate_adopted(adoptionProcess.getDate_adopted());
            a.setDate_published(adoptionProcess.getDate_published());
            a.setPet(adoptionProcess.getPet());
            a.setPoster(adoptionProcess.getPoster());
            a.setDescription(adoptionProcess.getDescription());
            return adoptionProcessRepository.save(a);
        }).orElse(null);
    }

    @Override
    public List<AdoptionProcessDTO> findByPet(PetDTO pet) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        adoptionProcessRepository.deleteById(id);
    }
}
