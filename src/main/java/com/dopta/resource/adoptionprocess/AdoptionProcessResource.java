package com.dopta.resource.adoptionprocess;

import com.dopta.resource.UserResource;
import com.dopta.resource.pet.PetResource;
import lombok.Data;

@Data
public class AdoptionProcessResource {
    private Integer id;
    private java.util.Date date_published;
    private String description;
    private java.util.Date date_adopted;
    private PetResource pet;
    private UserResource poster;
}
