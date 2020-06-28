package com.tutorial.crud.resource.pet;

import com.sun.istack.NotNull;
import com.tutorial.crud.entity.Sex;
import com.tutorial.crud.entity.Size;
import com.tutorial.crud.entity.Species;
import lombok.Data;

import javax.persistence.*;

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
