package com.dopta.service.Impl;

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
@Service
public class AdoptionProcessServiceImpl implements AdoptionProcessService {

    @Autowired
    private AdoptionProcessRepository adoptionProcessRepository;

    @Override
    public AdoptionProcess getAdoption(Integer id) {
        return adoptionProcessRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AdoptionProcess save(AdoptionProcess adoptionProcess) {
        return adoptionProcessRepository.save(adoptionProcess);
    }

    @Override
    public Optional<AdoptionProcess> findById(Integer id) {
        return adoptionProcessRepository.findById(id);
    }

    @Override
    public List<AdoptionProcess> listAllAdoptions() {
        return adoptionProcessRepository.findAll();
    }

    @Override
    @Transactional
    public AdoptionProcess edit(AdoptionProcess adoptionProcess, Integer id) {
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
    public List<AdoptionProcess> findByPet(Pet pet) {
        return adoptionProcessRepository.findByPet(pet);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        adoptionProcessRepository.deleteById(id);
    }
}
