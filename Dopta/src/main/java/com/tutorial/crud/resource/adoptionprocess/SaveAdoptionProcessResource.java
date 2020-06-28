package com.tutorial.crud.resource.adoptionprocess;

import com.sun.istack.NotNull;
import com.tutorial.crud.entity.Person;
import com.tutorial.crud.entity.Pet;
import lombok.Data;

import javax.persistence.*;
@Data
public class SaveAdoptionProcessResource {
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_published;
    @NotNull
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_adopted;

}
