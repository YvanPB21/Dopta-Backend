package com.tutorial.crud.resource.adoptionprocess;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
