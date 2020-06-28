package com.tutorial.crud.resource.adoptionprocess;

import com.sun.istack.NotNull;
import com.tutorial.crud.entity.Person;
import com.tutorial.crud.entity.Pet;
import lombok.Data;

import javax.persistence.*;
@Data
public class AdoptionProcessResource {
    private Integer id;
    private java.util.Date date_published;
    private String description;
    private java.util.Date date_adopted;

}
