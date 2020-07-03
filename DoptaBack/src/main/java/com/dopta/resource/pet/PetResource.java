package com.dopta.resource.pet;

import com.dopta.model.Species;
import com.dopta.resource.sex.SexResource;
import com.dopta.resource.size.SizeResource;
import lombok.Data;

@Data
public class PetResource {

    private Integer id;
    private String name;
    private java.util.Date date_of_birth;
    private Byte is_adopted;
    private String image_url;
    private Integer specieId;
    private Species species;
    private SizeResource size;
    private SexResource sex;
}
