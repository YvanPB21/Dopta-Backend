package com.tutorial.crud.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter @Setter
public class PersonResource {
    private String names;
    private String last_names;
    private Integer dni;
    private java.util.Date date_of_birth;
    private Float rating;
}
