package com.dopta.resource.pet;

import com.dopta.resource.sex.SexResource;
import com.dopta.resource.size.SizeResource;
import com.dopta.resource.species.SpeciesResource;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
public class SavePetResource {

    private String name;
    private java.util.Date date_of_birth;
    private Byte is_adopted;
    private String image_url;
    private SpeciesResource species;
    private SizeResource size;
    private SexResource sex;
}
