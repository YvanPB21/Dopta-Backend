package com.dopta.resource.pet;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
public class SavePetResource {

    @NotNull
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_birth;
    @NotNull
    private Byte is_adopted;
    private String image_url;
}
