package com.dopta.dto.adoptionprocess;

import com.dopta.model.Person;
import com.dopta.model.Pet;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
public class AdoptionProcessDTO {
    private Integer id;
    private String poster;
    private String pet;
    private Date date_published;
    private String description;
    private Date date_adopted;
    private String adopter;
}
