package com.tutorial.crud.resource;

import com.tutorial.crud.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter @Setter
public class PersonResource extends AuditModel {
    private String names;
    private String last_names;
    private Integer dni;
    private java.util.Date date_of_birth;
}
