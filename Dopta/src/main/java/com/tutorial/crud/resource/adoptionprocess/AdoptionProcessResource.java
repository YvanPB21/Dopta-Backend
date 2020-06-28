package com.tutorial.crud.resource.adoptionprocess;

import lombok.Data;

@Data
public class AdoptionProcessResource {
    private Integer id;
    private java.util.Date date_published;
    private String description;
    private java.util.Date date_adopted;

}
