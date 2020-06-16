package com.dopta.dto.adoptionprocess;

import lombok.Data;

import java.util.Date;

@Data
public class EditAdoptionProcessDTO {
    private String pet;
    private String description;
    private Date date_adopted;
    private String adopter;
}
