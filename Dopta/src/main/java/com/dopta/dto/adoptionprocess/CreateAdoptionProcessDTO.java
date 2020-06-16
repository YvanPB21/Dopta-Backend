package com.dopta.dto.adoptionprocess;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAdoptionProcessDTO {

    private Integer id;
    private String poster;
    private Integer pet;
    private Date date_published;
    private String description;
    private Date date_adopted;
    private String adopter;
}
