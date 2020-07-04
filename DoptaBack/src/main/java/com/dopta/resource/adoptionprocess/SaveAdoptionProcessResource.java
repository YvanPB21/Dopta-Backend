package com.dopta.resource.adoptionprocess;

import com.dopta.resource.UserResource;
import com.dopta.resource.pet.PetResource;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
public class SaveAdoptionProcessResource {
    private java.util.Date date_published;
    private String description;
    private java.util.Date date_adopted;
    private PetResource pet;
    private UserResource poster;
}
