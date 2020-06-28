package com.tutorial.crud.resource;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter @Setter
public class SavePersonResource {
    @NotNull
    private String names;
    @NotNull
    private String last_names;
    @NotNull
    private Integer dni;
    @NotNull
    private java.util.Date date_of_birth;
}